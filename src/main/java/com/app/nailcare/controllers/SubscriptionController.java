package com.app.nailcare.controllers;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.models.Subscription;
import com.app.nailcare.requests.SubscriptionRequest;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    public ResponseEntity<APIResponse<Subscription>> create(@RequestBody SubscriptionRequest payload){
        return  ResponseEntity
                .created(URI.create("/api/v1/subscriptions"))
                .body(new APIResponse<>(subscriptionService.create(payload.getCoverageId()), "success"));
    }
}
