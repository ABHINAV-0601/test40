package com.michaels.designhub.service;

import com.michaels.designhub.dto.TrackingNumberDto;

import java.util.List;

public interface ItrackingUpdates {

    Object getOrderTrackingMapping(TrackingNumberDto trackingNumberDto);

    void processTrackingNumbersUpdate(Object rawResult, TrackingNumberDto trackingNumberDto, List<String> unsuccessfulUpdates);
}
