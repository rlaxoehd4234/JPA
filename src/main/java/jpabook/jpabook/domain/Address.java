package jpabook.jpabook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {
    private String city;

    private String street;

    private String zipcode;


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street =street;
        this.zipcode = zipcode;
    }


}
