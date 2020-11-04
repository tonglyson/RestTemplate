package com.example.demo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "tsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class TsRequest {
    protected GroupType group;
    protected User user;
}
