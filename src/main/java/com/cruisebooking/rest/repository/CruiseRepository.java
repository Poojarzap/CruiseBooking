package com.cruisebooking.rest.repository;

import com.cruisebooking.rest.model.CruiseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CruiseRepository extends MongoRepository<CruiseModel, String> {
    List<CruiseModel> findBySourceAndDestination(String source, String destination);
    @Query("{ 'price' : { $gte: ?0, $lte: ?1 } }")
    List<CruiseModel> findByPriceBetween(String startPrice, String endPrice);

}
