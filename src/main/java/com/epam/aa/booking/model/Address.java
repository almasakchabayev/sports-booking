package com.epam.aa.booking.model;

public class Address extends BaseEntity {
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String zipCode;
    private GeoLocation geolocation; // for map purposes, latitude longitutede
}
