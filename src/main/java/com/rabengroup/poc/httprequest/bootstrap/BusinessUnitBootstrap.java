package com.rabengroup.poc.httprequest.bootstrap;

import com.rabengroup.poc.httprequest.model.Address;
import com.rabengroup.poc.httprequest.model.BusinessUnit;
import com.rabengroup.poc.httprequest.repositories.BusinessUnitRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusinessUnitBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        BusinessUnit rlp = new BusinessUnit("Raben Logistics Polska","7773249627");
        Address rlpAddress = new Address("Zbożowa 1", "62-023", "Robakowo", "Polska");
        rlp.setAddress(rlpAddress);

        businessUnitRepository.save(rlp);
        log.info("Saved business unit " + rlp);

        BusinessUnit flp = new BusinessUnit("Fresh Logistics Polska", "7773249610");
        Address flpAddress = new Address("Zbożowa 1", "62-023", "Robakowo", "Polska");
        flp.setAddress(flpAddress);

        businessUnitRepository.save(flp);
        log.info("Saved business unit " + flp);
    }
}
