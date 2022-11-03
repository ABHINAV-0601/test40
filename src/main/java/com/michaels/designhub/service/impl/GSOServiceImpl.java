package com.michaels.designhub.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.repository.ICommonDao;
import com.michaels.designhub.repository.OrderRepository;
import com.michaels.designhub.request.*;
import com.michaels.designhub.service.GSOService;
import com.michaels.designhub.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:55
 * @Version 1.0
 */
@Service
@Slf4j
public class GSOServiceImpl implements GSOService {

    @Autowired
    private OrderRepository orderRepository;

    @Value("${dotNet.uri}")
    private String dotNetUrl;
    @Value("${dotNet.host}")
    private String dotNetHost;
    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private RestTemplate restTemplate;

    public SearchGSOAndLayoutOptimizationResponse utilsGso(SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception {
        JSONObject requestJson = JSON.parseObject(JSON.toJSONString(searchGSOAndLayoutOptimizationRequest));
        Object object = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = orderRepository.get_gso_glasstype_req(requestJson.toJSONString());

        JSONObject jsonObject = JSON.parseObject(jsonNode.toString());
        GetGsoGlassTypeReqRet getGsoGlassTypeReqRet = JSONObject.toJavaObject(jsonObject, GetGsoGlassTypeReqRet.class);
        DotNetResponse MPDotNetResponse = new DotNetResponse();
        DotNetResponse CPDotNetResponse = new DotNetResponse();
        JSONObject CPString = null;
        JSONObject MPString = null;
        if (getGsoGlassTypeReqRet.getDetails() == null || getGsoGlassTypeReqRet.getDetails().size() == 0){
            throw new Exception("No relevant data found");
        }
        for (Details details : getGsoGlassTypeReqRet.getDetails()) {
            if ("MP".equals(details.getGlass_type())) {
                String req_info = JSON.toJSONString(details.getReq_info());
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                HttpEntity<String> formEntity = new HttpEntity<String>(req_info, headers);
                try {
                    ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(dotNetHost + dotNetUrl, formEntity, String.class);
                    MPString = JSON.parseObject(stringResponseEntity.getBody());
                    MPDotNetResponse = JSONObject.toJavaObject(MPString, DotNetResponse.class);
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }

                log.info("MP = " + MPDotNetResponse.toString());
            } else if ("CP".equals(details.getGlass_type())) {
                String req_info = JSON.toJSONString(details.getReq_info());
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                HttpEntity<String> formEntity = new HttpEntity<String>(req_info, headers);
                try {
                    ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(dotNetHost + dotNetUrl, formEntity, String.class);
                    CPString = JSON.parseObject(stringResponseEntity.getBody());
                    CPDotNetResponse = JSONObject.toJavaObject(CPString, DotNetResponse.class);
                } catch (Exception e){
                    log.error("Request herokuApp fail, Status is CP");
                }
                log.info("CP = " + CPDotNetResponse.toString());
            }
        }
        GetGsoNonPrintedLayout getGsoNonPrintedLayout = new GetGsoNonPrintedLayout();
        getGsoNonPrintedLayout.setLocale(searchGSOAndLayoutOptimizationRequest.getLocale());
        getGsoNonPrintedLayout.setStore_id(searchGSOAndLayoutOptimizationRequest.getStore_id());
        if (MPDotNetResponse.getUnoptimized_order_parts() != null || CPDotNetResponse.getUnoptimized_order_parts() != null) {
            List<GetGsoNonPrintedLayoutDetails> list = new ArrayList<>();
            if (MPDotNetResponse.getUnoptimized_order_parts() != null && MPDotNetResponse.getUnoptimized_order_parts().size() != 0) {
                GetGsoNonPrintedLayoutDetails details = new GetGsoNonPrintedLayoutDetails();
                details.setGlass_type("MP");
                GetGsoNonPrintedLayoutReqInfo reqInfo = new GetGsoNonPrintedLayoutReqInfo();
                reqInfo.setUnoptimized_order_parts(MPDotNetResponse.getUnoptimized_order_parts());
                details.setReq_info(reqInfo);
                list.add(details);
            }
            if (CPDotNetResponse.getUnoptimized_order_parts() != null && CPDotNetResponse.getUnoptimized_order_parts().size() != 0) {
                GetGsoNonPrintedLayoutDetails details = new GetGsoNonPrintedLayoutDetails();
                details.setGlass_type("CP");
                GetGsoNonPrintedLayoutReqInfo reqInfo = new GetGsoNonPrintedLayoutReqInfo();
                reqInfo.setUnoptimized_order_parts(CPDotNetResponse.getUnoptimized_order_parts());
                details.setReq_info(reqInfo);
                list.add(details);
            }
            getGsoNonPrintedLayout.setDetails(list);
            requestJson = JSON.parseObject(JSON.toJSONString(getGsoNonPrintedLayout));
            System.out.println(requestJson.toJSONString());
            JsonNode jsonNode1 = orderRepository.get_gso_non_printed_layout(requestJson.toJSONString());
            JSONObject jsonObject1 = JSON.parseObject(jsonNode1.toString());
            NonPrintedLayoutParent nonPrintedLayoutParent = JSONObject.toJavaObject(jsonObject1, NonPrintedLayoutParent.class);
            List<Integer> ids = new ArrayList<>();
            // update
            for (int i = 0; i < searchGSOAndLayoutOptimizationRequest.getOrder_lineitem_ids().size(); i++) {
                ids.add(searchGSOAndLayoutOptimizationRequest.getOrder_lineitem_ids().get(i));
            }

            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

            orderRepository.updateOrderLineitem(sdf.format(new Date(System.currentTimeMillis())), ids);

            SearchGSOAndLayoutOptimizationResponse searchGSOAndLayoutOptimizationResponse = new SearchGSOAndLayoutOptimizationResponse();
            List<PrintedLayout> printedLayoutList = new ArrayList<>();
            searchGSOAndLayoutOptimizationResponse.setStore_id(searchGSOAndLayoutOptimizationRequest.getStore_id());
            searchGSOAndLayoutOptimizationResponse.setPrinted_layout(printedLayoutList);
            searchGSOAndLayoutOptimizationResponse.setNon_printed_layout(nonPrintedLayoutParent);
            if (CPString != null) {
                PrintedLayout cp = new PrintedLayout();
                cp.setGlass_type("CP");
                ResponseInfo response_info = JSONObject.toJavaObject(CPString, ResponseInfo.class);
                cp.setResponse_info(response_info);
                printedLayoutList.add(cp);
            }
            if (MPString != null) {
                PrintedLayout mp = new PrintedLayout();
                mp.setGlass_type("MP");
                ResponseInfo response_info = JSONObject.toJavaObject(MPString, ResponseInfo.class);
                mp.setResponse_info(response_info);
                printedLayoutList.add(mp);
            }
            return searchGSOAndLayoutOptimizationResponse;
        }
        return null;
    }

    @Override
    public Map<String, Object> utils(UtilsDto utilsDto) {
        log.debug("utils get data by :{}",utilsDto);
        Map<String, Object> result = new HashMap<>();
        result.put("module_name",utilsDto.getFunctionName());
        if(!utilsDto.getIsFunction().booleanValue()){
            String url = null;
            try {
                url = "http://"+utilsDto.getServiceHost()+":"+utilsDto.getServicePort()+utilsDto.getServiceUri();
                log.debug("utils get data url:{}",url);
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                // 封装HttpEntity
                HttpEntity formEntity = new HttpEntity(JSON.toJSONString(utilsDto.getOrderParts()), headers);
                String obj = restTemplate.postForObject(url, formEntity, String.class);
                if (!ObjectUtils.isEmpty(obj)) {
                    result.put("module_params",JSON.parseObject(obj));
                }else{
                    result.put("module_params","No Data Found");
                }
            } catch (Exception e) {
                log.error("url {} get data fail：{}",url,e.getMessage());
                result.put("module_params",e.getMessage());
            }
        }else{
            String  obj = commonDao.callFunction(utilsDto).toString();
            if (StringUtils.hasLength(obj)) {
                if(obj.startsWith("[")){
                    result.put("module_params",JSON.parseArray(obj));
                }else{
                    result.put("module_params",JSON.parseObject(obj));
                }
            }else{
                result.put("module_params","No Data Found");
            }
        }
        return result;
    }
}
