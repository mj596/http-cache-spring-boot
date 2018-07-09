package com.rabengroup.poc.httprequest.services;

import com.rabengroup.poc.httprequest.model.BusinessUnit;
import com.rabengroup.poc.httprequest.repositories.BusinessUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class BusinessUnitServiceCacheable {

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    public BusinessUnitServiceCacheable(BusinessUnitRepository businessUnitRepository) {
        this.businessUnitRepository = businessUnitRepository;
    }

    private static int invokeCounter = 0;

    @Cacheable("BusinessUnitService")
    public BusinessUnit getBusinessUnit(String businessUnit, Integer minResponseTime, Integer maxResponseTime) {

        invokeCounter++;

        log.info("Getting data for " + businessUnit + " from respository - " + invokeCounter);

        Random random = new Random();

        Integer responseTimeUpperBound = maxResponseTime - minResponseTime + 1;

        Integer responseTime = random.nextInt(responseTimeUpperBound) + minResponseTime;
        log.info("Random wait time is " + responseTime + " ms.");

        try {
            Thread.sleep(responseTime);
        } catch( InterruptedException e ) {

        }

        return businessUnitRepository.findByName(businessUnit);
    }
}
