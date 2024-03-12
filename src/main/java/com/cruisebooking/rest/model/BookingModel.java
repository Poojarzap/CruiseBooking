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

//    public BookingModel(String bookingId, UserModel bookingUser, CruiseModel bookingCruise) {
//        this.bookingId=bookingId;
//        this.bookingUser = bookingUser;
//        this.bookingCruise = bookingCruise;
//        // Ensure bookingSequence is not null before accessing its methods
////        if (bookingSequence != null) {
////            this.bookingId = String.valueOf(bookingSequence.getSequence());
////            bookingSequence.setSequence(bookingSequence.getSequence() + 1);
////        } else {
////            bookingSequence=new BookingSequence();
////            bookingSequence.setSequence(1);
////            this.bookingId = String.valueOf(bookingSequence.getSequence());
////            System.out.println("BookingSequence cannot be null.");
////        }
//    }


//    public BookingSequence getBookingSequence() {
//        return bookingSequence;
//    }
//
//    public void setBookingSequence(BookingSequence bookingSequence) {
//        this.bookingSequence = bookingSequence;
//    }


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

    @Override
    public String toString() {
        return "BookingModel{" +
                "bookingId='" + bookingId + '\'' +
                ", bookingUser='" + bookingUser + '\'' +
                ", bookingCruise='" + bookingCruise + '\'' +
                '}';
    }
}
