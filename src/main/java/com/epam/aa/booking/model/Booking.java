package com.epam.aa.booking.model;

import java.time.LocalDateTime;

public class Booking extends BaseEntity {
    private SportPlace sportPlace;
    private Customer customer;
//    private Payment payment;
    private LocalDateTime bookedFrom;
    private LocalDateTime bookedTo;
}
