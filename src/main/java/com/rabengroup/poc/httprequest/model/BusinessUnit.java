package com.rabengroup.poc.httprequest.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@XmlRootElement(name = "businessUnit")
public class BusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @XmlAttribute
    private long Id;

    @Column
    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column
    @NotNull
    private String vatNumber;

    public BusinessUnit(@NotNull String name, @NotNull String vatNumber) {
        this.name = name;
        this.vatNumber = vatNumber;
    }
}
