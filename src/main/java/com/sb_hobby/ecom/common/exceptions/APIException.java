package com.sb_hobby.ecom.common.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class APIException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public APIException(String message) {
        super(message);
    }
}
