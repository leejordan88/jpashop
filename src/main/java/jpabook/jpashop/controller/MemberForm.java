package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message="회원이름은 필수 입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;

    public Address createAddress () {
        return Address.builder()
                .city(getCity())
                .street(getStreet())
                .zipcode(getZipcode())
                .build();
    }


}
