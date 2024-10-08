package com.abcode.customerapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    SUCCESS("Success"),
    FAILED("Failed");
    private final String value;
}
