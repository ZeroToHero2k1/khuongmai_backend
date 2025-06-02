package com.zerotohero.khuongmaiapp.exception;

import com.zerotohero.khuongmaiapp.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value= KMAppException.class)
    ResponseEntity<ApiResponse> handleKMAppException(KMAppException kmAppException){
        ErrorCode errorCode=kmAppException.getErrorCode();
        ApiResponse apiResponse=ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessege())
                .build();
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value=Exception.class)
    ResponseEntity<ApiResponse> handleRuntimeException(){
        ApiResponse apiResponse=new ApiResponse().builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessege())
                .build();
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleValidException(MethodArgumentNotValidException exception){
        String enumkey=exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode=ErrorCode.ERRORCODE_INVALID;

        try {
            errorCode=ErrorCode.valueOf(enumkey);
        } catch (IllegalArgumentException e) {

        }
        ApiResponse apiResponse=ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessege())
                .build();
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }


}
