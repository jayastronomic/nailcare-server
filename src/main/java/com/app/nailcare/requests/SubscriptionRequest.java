package com.app.nailcare.requests;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SubscriptionRequest {
    private UUID coverageId;
}
