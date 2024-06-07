package com.michaels.designhub.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.michaels.designhub.dto.TrackingNumberDto;
import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.repository.ICommonDao;
import com.michaels.designhub.service.ItrackingUpdates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrackingUpdates implements ItrackingUpdates {

    @Autowired
    private ICommonDao commonDao;

    @Override
    public Object getOrderTrackingMapping(TrackingNumberDto trackingNumberDto) {
        String formattedFunctionParams = formatFunctionParams(trackingNumberDto.getStoreId(), trackingNumberDto.getTrackingNumbers());
        UtilsDto utilsDto = createUtilsDto("get_orders_by_tracking_numbers", formattedFunctionParams);
        Object rawResult = null;

        log.debug("Calling get_orders_by_tracking_numbers with UtilsDto: {}", utilsDto);
        rawResult = commonDao.callFunction(utilsDto);

        return rawResult;
    }

    @Override
    public void processTrackingNumbersUpdate(Object rawResult, TrackingNumberDto trackingNumberDto, List<String> unsuccessfulUpdates) {
        if (rawResult != null) {
            JSONArray resultsArray = JSON.parseArray(rawResult.toString());
            resultsArray.forEach(item -> {
                JSONObject result = (JSONObject) item;
                String trackingNumber = result.getString("tracking_number");
                log.debug("Processing update for tracking number: {}", trackingNumber);

                JSONArray orderIds = result.getJSONArray("order_ids");
                if (!updateOrderLineItems(trackingNumberDto, trackingNumber, orderIds)) {
                    unsuccessfulUpdates.add(trackingNumber);
                }
            });
        } else {
            log.warn("No result for tracking numbers update, marking all as failed.");
            unsuccessfulUpdates.addAll(trackingNumberDto.getTrackingNumbers());
        }
    }

    private String formatFunctionParams(String storeId, List<String> trackingNumbers) {
        String joinedTrackingNumbers = trackingNumbers.stream()
                .map(number -> "'" + number.replace("'", "''") + "'") // Safely quote and escape single quotes
                .collect(Collectors.joining(","));
        return String.format("'%s', ARRAY[%s]", storeId, joinedTrackingNumbers);
    }

    private UtilsDto createUtilsDto(String functionName, String functionParams) {
        UtilsDto utilsDto = new UtilsDto();
        utilsDto.setFunctionName(functionName);
        utilsDto.setIsFunction(true);
        utilsDto.setFunctionParams(functionParams);
        return utilsDto;
    }

    private boolean updateOrderLineItems(TrackingNumberDto trackingNumberDto, String trackingNumber, JSONArray orderIds) {

        JSONObject updateParams = new JSONObject();
        updateParams.put("store_id", trackingNumberDto.getStoreId());


        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
        LocalDateTime dateTime = LocalDateTime.parse(trackingNumberDto.getReceivedAt(), inputFormatter);

        // Create a formatter to format with 3 milliseconds
        DateTimeFormatter outputFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 3, 3, true)
                .appendLiteral('Z')
                .toFormatter();

        // Format the LocalDateTime to the desired string representation
        String formattedDateTime = dateTime.format(outputFormatter);

        updateParams.put("received_by", trackingNumberDto.getReceivedBy());

        updateParams.put("received_at", formattedDateTime);
        updateParams.put("order_lineitems", orderIds);
        updateParams.put("tracking_number", trackingNumber);
        updateParams.put("received", true);

        String formattedUpdateParams = "'" + updateParams.toJSONString() + "'";
        UtilsDto updateDto = createUtilsDto("update_order_lineitem_shipping", formattedUpdateParams);

        log.debug("Updating order line items with: {}", updateDto);
        Object updateResponse = commonDao.callFunction(updateDto);
        log.debug("Update response: {}", updateResponse);

        JSONObject jsonResponse = JSON.parseObject(updateResponse.toString());
        int statusCode = jsonResponse.getIntValue("status_code");
        return statusCode == 1;
    }
}
