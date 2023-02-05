package jpabook.jpabook.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberForm {

    private String name;

    private String city;

    private String street;

    private String zipcode;
}
