package com.itfirma.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OsobaException extends Exception{

    public OsobaException(String message) {
        super(message);
    }

}
