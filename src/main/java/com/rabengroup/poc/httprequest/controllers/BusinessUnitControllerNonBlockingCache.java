package com.rabengroup.poc.httprequest.controllers;

import com.rabengroup.poc.httprequest.model.BusinessUnit;
import com.rabengroup.poc.httprequest.services.BusinessUnitService;
import com.rabengroup.poc.httprequest.services.BusinessUnitServiceCacheable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@RestController
@Slf4j
@RequestMapping("/non-blocking-cache")
public class BusinessUnitControllerNonBlockingCache {

    @Autowired
    private BusinessUnitServiceCacheable businessUnitService;

    @Autowired
    private Executor executor;

    private static int invokeCounter = 0;

    @RequestMapping(value = "/json/completableFuture/businessUnit", method = RequestMethod.GET, produces = "application/json")
    private CompletableFuture<ResponseEntity<?>> getBusinessUnitCompletableFutureJSON(
            @RequestParam(value = "businessUnit", defaultValue = "Raben Logitics Polska") String businessUnit,
            @RequestParam(value = "minResponseTime", defaultValue = "500") Integer minResponseTime,
            @RequestParam(value = "maxResponseTime", defaultValue = "2000") Integer maxResponseTime) {

        invokeCounter++;

        log.info("Non-blocking request (CompletableFuture) - " + invokeCounter);

        CompletableFuture<ResponseEntity<?>> result = CompletableFuture.supplyAsync(
                () -> getBusinessUnit(businessUnit, minResponseTime, maxResponseTime),
                executor
        );

        log.info("Release thread.");

        return result;
    }

    private ResponseEntity<BusinessUnit> getBusinessUnit(String businessUnit, Integer minResponseTime, Integer maxResponseTime) {
        return ResponseEntity.ok(businessUnitService.getBusinessUnit(businessUnit, minResponseTime, maxResponseTime));
    }

}
