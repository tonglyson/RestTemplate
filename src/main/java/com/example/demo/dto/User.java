package com.example.demo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "fullName")
    private String fullName;
    @XmlAttribute(name = "email")
    private String email;
    @XmlAttribute(name = "password")
    private String password;
    @XmlAttribute(name ="name")
    private String name;
    @XmlAttribute(name = "siteRole")
    private String siteRole;
    @XmlAttribute(name = "authSetting")
    private String authSetting;
}
