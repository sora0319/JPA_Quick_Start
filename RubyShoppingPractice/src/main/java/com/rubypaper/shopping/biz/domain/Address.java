package com.rubypaper.shopping.biz.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;

    private String roadName;

    private String zipCode;
}