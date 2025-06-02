package com.zerotohero.khuongmaiapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KMAppException extends RuntimeException {
    private ErrorCode errorCode;

    public KMAppException(ErrorCode errorCode) {
        super(errorCode.getMessege());
        this.errorCode=errorCode;
    }
}
