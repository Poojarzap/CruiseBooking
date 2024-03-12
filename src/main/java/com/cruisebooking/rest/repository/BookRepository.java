package com.cruisebooking.rest.repository;

import com.cruisebooking.rest.model.BookingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookRepository extends MongoRepository<BookingModel,String> {

}
