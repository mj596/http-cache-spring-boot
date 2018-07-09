package com.rabengroup.poc.httprequest.repositories;

import com.rabengroup.poc.httprequest.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
