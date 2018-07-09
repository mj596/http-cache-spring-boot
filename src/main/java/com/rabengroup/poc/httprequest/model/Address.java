package com.rabengroup.poc.httprequest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @XmlAttribute
    private long Id;

    @Column
    @NotNull
    private String street;

    @Column
    @NotNull
    private String postCode;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private String country;

    public Address(@NotNull String street, @NotNull String postCode, @NotNull String city, @NotNull String country) {
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }
}
