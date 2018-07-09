package com.rabengroup.poc.httprequest.repositories;

import com.rabengroup.poc.httprequest.model.BusinessUnit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BusinessUnitRepository extends CrudRepository<BusinessUnit, Long> {

    public BusinessUnit findByName(String name);
}
