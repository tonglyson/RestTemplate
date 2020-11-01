package com.example.demo.dto.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    @XmlAttribute(name="name")
    private String name;
}
