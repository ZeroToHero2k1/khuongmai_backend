package com.zerotohero.khuongmaiapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ROLE_EXISTED(1001,"Đã tồn tại role này", HttpStatus.BAD_REQUEST),
    DEPARTMENT_EXISTED(1002,"Đã tồn tại trụ sở này", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(999,"Lỗi 500, team backend nên xem lại logic",HttpStatus.INTERNAL_SERVER_ERROR),
    ERRORCODE_INVALID(998,"Điền sai errorcode rồi bạn",HttpStatus.BAD_REQUEST),
    ROLE_MIN_INVALID(997,"Số lượng ko được dưới 3 phần tử",HttpStatus.BAD_REQUEST),
    DEPARTMENT_IS_NOT_EXISTED(996,"Không tồn tại trụ sở này",HttpStatus.BAD_REQUEST)
    ;


    private int code;
    private String messege;
    private HttpStatusCode statusCode;
}
