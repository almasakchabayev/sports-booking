package com.epam.aa.booking.model;

import java.util.List;

public class Customer extends User {
    private Company company;
    private List<SportPlace> favourited;
    private List<SportPlace> booked;
    //TODO: split to Company and Person
}
