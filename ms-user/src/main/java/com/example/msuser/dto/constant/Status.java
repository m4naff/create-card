package com.example.msuser.dto.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum Status {
    ACTIVE(1), DE_ACTIVE(0);
    int status;
}
