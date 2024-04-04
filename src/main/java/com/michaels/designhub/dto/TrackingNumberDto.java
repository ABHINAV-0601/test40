package com.michaels.designhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackingNumberDto {

    @JsonProperty("store_id")
    private String storeId;

    @JsonProperty("received_by")
    private String receivedBy;

    @JsonProperty("received_at")
    private String receivedAt;

    @JsonProperty("received")
    private String received;

    @JsonProperty("tracking_numbers")
    private List<String> trackingNumbers;
}
