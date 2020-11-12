package com.example.demo.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorDetail {
    private String message;
    private LocalDateTime time;
    private String detail;
}
