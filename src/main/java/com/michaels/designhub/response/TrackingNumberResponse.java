package com.michaels.designhub.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackingNumberResponse {

    private String status_message;

    private int status_code;

    private String status_description;

    private List<String> failed_tracking_numbers;
}
