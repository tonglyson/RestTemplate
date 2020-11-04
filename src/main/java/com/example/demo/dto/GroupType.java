package com.example.demo.dto;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupType {
    @XmlAttribute(name="name")
    private String name;
}
