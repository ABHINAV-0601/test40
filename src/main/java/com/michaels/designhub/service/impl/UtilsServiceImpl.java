package com.michaels.designhub.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.michaels.designhub.dto.TrackingNumberDto;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.repository.ICommonDao;
import com.michaels.designhub.repository.OrderRepository;
import com.michaels.designhub.repository.TrainingLogRepository;
import com.michaels.designhub.request.*;
import com.michaels.designhub.response.*;
import com.michaels.designhub.service.ItrackingUpdates;
import com.michaels.designhub.service.UtilsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Baojian Hong
 * @Date 2022/8/31 14:55
 * @Version 1.0
 */
@Service
@Slf4j
public class UtilsServiceImpl implements UtilsService {

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";
    public static final String ACCEPT = "Accept";

    public static final String HEADER_TOKEN = "token";

    public static final String ACCESS_TOKEN ="access-token";
    public static final String MODULE_PARAMS = "module_params";
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

    @Autowired
    private ItrackingUpdates trackingUpdate;

    @Autowired
    private TrainingLogRepository trainingLogRepository;


    public SearchGSOAndLayoutOptimizationResponse utilsGso(SearchGSOAndLayoutOptimizationRequest searchGSOAndLayoutOptimizationRequest) throws Exception {
        log.debug("SearchGSOAndLayoutOptimizationResponse - utils Gso params : {},",searchGSOAndLayoutOptimizationRequest);
        JSONObject requestJson = JSON.parseObject(JSON.toJSONString(searchGSOAndLayoutOptimizationRequest));
        JsonNode jsonNode = orderRepository.get_gso_glasstype_req(requestJson.toJSONString());

        JSONObject jsonObject = JSON.parseObject(jsonNode.toString());
        GetGsoGlassTypeReqRet getGsoGlassTypeReqRet = JSON.toJavaObject(jsonObject, GetGsoGlassTypeReqRet.class);
        DotNetResponse MPDotNetResponse = new DotNetResponse();
        DotNetResponse CPDotNetResponse = new DotNetResponse();
        JSONObject CPString = null;
        JSONObject MPString = null;
        if (getGsoGlassTypeReqRet.getDetails() == null || getGsoGlassTypeReqRet.getDetails().isEmpty()){
            log.error("SearchGSOAndLayoutOptimizationResponse - No relevant data found");
            throw new Exception("SearchGSOAndLayoutOptimizationResponse - No relevant data found");
        }
        for (Details details : getGsoGlassTypeReqRet.getDetails()) {
            if ("MP".equals(details.getGlass_type())) {
                String req_info = JSON.toJSONString(details.getReq_info());
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType(APPLICATION_JSON_CHARSET_UTF_8);
                headers.setContentType(type);
                headers.add(ACCEPT, MediaType.APPLICATION_JSON.toString());
                HttpEntity<String> formEntity = new HttpEntity<>(req_info, headers);
                try {
                    ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(dotNetHost + dotNetUrl, formEntity, String.class);
                    MPString = JSON.parseObject(stringResponseEntity.getBody());
                    MPDotNetResponse = JSON.toJavaObject(MPString, DotNetResponse.class);
                } catch (Exception e){
                    log.error("SearchGSOAndLayoutOptimizationResponse - Exception:{}",e.getMessage());
                    throw new Exception(e.getMessage());
                }
                log.debug("SearchGSOAndLayoutOptimizationResponse - MP = {}", MPDotNetResponse.toString());
            } else if ("CP".equals(details.getGlass_type())) {
                String req_info = JSON.toJSONString(details.getReq_info());
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType(APPLICATION_JSON_CHARSET_UTF_8);
                headers.setContentType(type);
                headers.add(ACCEPT, MediaType.APPLICATION_JSON.toString());
                HttpEntity<String> formEntity = new HttpEntity<>(req_info, headers);
                try {
                    ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(dotNetHost + dotNetUrl, formEntity, String.class);
                    CPString = JSON.parseObject(stringResponseEntity.getBody());
                    CPDotNetResponse = JSON.toJavaObject(CPString, DotNetResponse.class);
                } catch (Exception e){
                    log.error("SearchGSOAndLayoutOptimizationResponse - Request herokuApp fail, Status is CP");
                }
                log.debug("SearchGSOAndLayoutOptimizationResponse - CP = " + CPDotNetResponse.toString());
            }
        }
        GetGsoNonPrintedLayout getGsoNonPrintedLayout = new GetGsoNonPrintedLayout();
        getGsoNonPrintedLayout.setLocale(searchGSOAndLayoutOptimizationRequest.getLocale());
        getGsoNonPrintedLayout.setStore_id(searchGSOAndLayoutOptimizationRequest.getStore_id());
        if (MPDotNetResponse.getUnoptimized_order_parts() != null || CPDotNetResponse.getUnoptimized_order_parts() != null) {
            List<GetGsoNonPrintedLayoutDetails> list = new ArrayList<>();
            if (MPDotNetResponse.getUnoptimized_order_parts() != null && !MPDotNetResponse.getUnoptimized_order_parts().isEmpty()) {
                GetGsoNonPrintedLayoutDetails details = new GetGsoNonPrintedLayoutDetails();
                details.setGlass_type("MP");
                GetGsoNonPrintedLayoutReqInfo reqInfo = new GetGsoNonPrintedLayoutReqInfo();
                reqInfo.setUnoptimized_order_parts(MPDotNetResponse.getUnoptimized_order_parts());
                details.setReq_info(reqInfo);
                list.add(details);
            }
            if (CPDotNetResponse.getUnoptimized_order_parts() != null && !CPDotNetResponse.getUnoptimized_order_parts().isEmpty()) {
                GetGsoNonPrintedLayoutDetails details = new GetGsoNonPrintedLayoutDetails();
                details.setGlass_type("CP");
                GetGsoNonPrintedLayoutReqInfo reqInfo = new GetGsoNonPrintedLayoutReqInfo();
                reqInfo.setUnoptimized_order_parts(CPDotNetResponse.getUnoptimized_order_parts());
                details.setReq_info(reqInfo);
                list.add(details);
            }
            getGsoNonPrintedLayout.setDetails(list);
            requestJson = JSON.parseObject(JSON.toJSONString(getGsoNonPrintedLayout));
            log.debug("requestJson = {} ",requestJson.toJSONString());
            JsonNode jsonNode1 = orderRepository.get_gso_non_printed_layout(requestJson.toJSONString());
            JSONObject jsonObject1 = JSON.parseObject(jsonNode1.toString());
            NonPrintedLayoutParent nonPrintedLayoutParent = JSON.toJavaObject(jsonObject1, NonPrintedLayoutParent.class);
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
            searchGSOAndLayoutOptimizationResponse.setStoreId(searchGSOAndLayoutOptimizationRequest.getStore_id());
            searchGSOAndLayoutOptimizationResponse.setPrintedLayouts(printedLayoutList);
            searchGSOAndLayoutOptimizationResponse.setNonPrintedLayoutParent(nonPrintedLayoutParent);
            if (CPString != null) {
                PrintedLayout cp = new PrintedLayout();
                cp.setGlassType("CP");
                ResponseInfo response_info = JSON.toJavaObject(CPString, ResponseInfo.class);
                cp.setResponseInfo(response_info);
                printedLayoutList.add(cp);
            }
            if (MPString != null) {
                PrintedLayout mp = new PrintedLayout();
                mp.setGlassType("MP");
                ResponseInfo response_info = JSON.toJavaObject(MPString, ResponseInfo.class);
                mp.setResponseInfo(response_info);
                printedLayoutList.add(mp);
            }
            return searchGSOAndLayoutOptimizationResponse;
        }
        log.debug("SearchGSOAndLayoutOptimizationResponse - result is null");
        return null;
    }

    @Override
    public Map<String, Object> utils(UtilsDto utilsDto, HttpServletRequest httpServletRequest) {
        log.debug("utils - utils get data params :{}",utilsDto);
        Map<String, Object> result = new HashMap<>();
        result.put("module_name",utilsDto.getFunctionName());

        if(!utilsDto.getIsFunction().booleanValue()){
            String accessToken = (String) httpServletRequest.getAttribute(ACCESS_TOKEN);
            String url = null;
            try {
                String port = "";
                if (StringUtils.isNotBlank(utilsDto.getServicePort()))
                    port = ":"+utilsDto.getServicePort();

                url = utilsDto.getServiceHost()+port+utilsDto.getServiceUri();

                log.debug("utils get data url:{} ",url);
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType(APPLICATION_JSON_CHARSET_UTF_8);
                headers.setContentType(type);
                headers.add(ACCEPT, MediaType.APPLICATION_JSON.toString());
                headers.add(HEADER_TOKEN, accessToken);
                // HttpEntity
                HttpEntity<?> formEntity = new HttpEntity<>(JSON.toJSONString(utilsDto.getServiceParams()), headers);
                String obj = restTemplate.postForObject(url, formEntity, String.class);
                if (!ObjectUtils.isEmpty(obj)) {
                    result.put(MODULE_PARAMS,JSON.parseObject(obj));
                }else{
                    result.put(MODULE_PARAMS,"No Data Found");
                }
            } catch (Exception e) {
                log.error("Exception occurred while calling Utils API with is_a_function={}, function_name={} ,function_params={}",utilsDto.getIsFunction(),utilsDto.getFunctionName(),utilsDto.getFunctionParams(),e);
                result.put(MODULE_PARAMS,e.getMessage());
            }
        }else{
            Object  obj = commonDao.callFunction(utilsDto);
            if (Objects.nonNull(obj)) {
                String response = obj.toString();
                if(response.startsWith("[")){
                    result.put(MODULE_PARAMS,JSON.parseArray(response));
                }else{
                    result.put(MODULE_PARAMS,JSON.parseObject(response));
                }
            }else{
                log.debug("utils - No Data Found.");
                result.put(MODULE_PARAMS,"No Data Found");
            }
        }
        return result;
    }

    /**
     * @param trainingLog
     * @return
     */
    @Override
    public Integer saveTrainingLog(TrainingLog trainingLog) {
        if (trainingLog.getCreated_at() == null) {
            trainingLog.setCreated_at(new Timestamp(System.currentTimeMillis()));
        }
        return trainingLogRepository.save(trainingLog).getId();
    }

    /**
     * @param id
     */
    @Override
    public void exitTrainingLog(Integer id) {
        TrainingLog entry = trainingLogRepository.getOne(id);
        entry.setExited_at(new Timestamp(System.currentTimeMillis()));
        trainingLogRepository.save(entry);
    }

    @Override
    public TrackingNumberResponse updateTrackingNumbers(TrackingNumberDto trackingNumberDto) {
        log.info("Starting update of tracking numbers as received: {}", trackingNumberDto);

        Object rawResult = trackingUpdate.getOrderTrackingMapping(trackingNumberDto);
        List<String> unsuccessfulUpdates = new ArrayList<>();

        log.info("Raw result from get_orders_by_tracking_numbers: {}", rawResult);

        trackingUpdate.processTrackingNumbersUpdate(rawResult, trackingNumberDto, unsuccessfulUpdates);

        return buildTrackingNumberResponse(unsuccessfulUpdates);
    }

    private static TrackingNumberResponse buildTrackingNumberResponse(List<String> failedTrackingList) {
        TrackingNumberResponse response = new TrackingNumberResponse();
        if (!failedTrackingList.isEmpty()) {
            response.setStatus_code(500);
            response.setStatus_message("Failed");
            response.setStatus_description("Failed to update one or more tracking numbers.");
            response.setFailed_tracking_numbers(failedTrackingList);
        } else {
            response.setStatus_code(200);
            response.setStatus_message("Success");
            response.setStatus_description("All tracking numbers updated successfully.");
        }
        return response;
    }
}
