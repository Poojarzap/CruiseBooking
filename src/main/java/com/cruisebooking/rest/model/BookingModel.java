package com.cruisebooking.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "BookingDetails")
public class BookingModel {

    @Id
    @Field("bookingId")
    private String bookingId; // Use int for auto-incrementing ID
    private String bookingUser;
    private String bookingCruise;


    public BookingModel() {
    }

    public BookingModel(String bookingId, String bookingUser, String bookingCruise) {
        this.bookingId = bookingId;
        this.bookingUser = bookingUser;
        this.bookingCruise = bookingCruise;
    }


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }


    public String getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(String bookingUser) {
        this.bookingUser = bookingUser;
    }

    public String getBookingCruise() {
        return bookingCruise;
    }

    public void setBookingCruise(String bookingCruise) {
        this.bookingCruise = bookingCruise;
    }


}
