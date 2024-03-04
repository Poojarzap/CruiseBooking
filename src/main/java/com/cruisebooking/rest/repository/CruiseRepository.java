package com.cruisebooking.rest.repository;

import com.cruisebooking.rest.model.CruiseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CruiseRepository extends MongoRepository<CruiseModel, String> {

}
