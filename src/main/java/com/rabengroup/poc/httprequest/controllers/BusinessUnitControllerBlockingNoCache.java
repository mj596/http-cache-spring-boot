package com.rabengroup.poc.httprequest.controllers;

import com.rabengroup.poc.httprequest.model.BusinessUnit;
import com.rabengroup.poc.httprequest.services.BusinessUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@Slf4j
@RequestMapping("/blocking-no-cache")
public class BusinessUnitControllerBlockingNoCache {

    @Autowired
    private BusinessUnitService businessUnitService;

    @Autowired
    private ExecutorService executorService;

    private static int invokeCounter = 0;

    @RequestMapping(value = "/json/businessUnit", method = RequestMethod.GET, produces = "application/json")
    private ResponseEntity<?> getBusinessUnitJSON(
            @RequestParam(value = "businessUnit", defaultValue = "Raben Logitics Polska") String businessUnit,
            @RequestParam(value = "minResponseTime", defaultValue = "500") Integer minResponseTime,
            @RequestParam(value = "maxResponseTime", defaultValue = "2000") Integer maxResponseTime) {

        invokeCounter++;

        log.info("Blocking request - " + invokeCounter);
        Future<BusinessUnit> futureResult = executorService.submit( () -> {
            return businessUnitService.getBusinessUnit(businessUnit, minResponseTime, maxResponseTime);
        });

        try {
            return ResponseEntity.ok(futureResult.get());
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        } catch (ExecutionException e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }

//        return ResponseEntity.ok(businessUnitService.getBusinessUnit(businessUnit, minResponseTime, maxResponseTime));
    }

}
