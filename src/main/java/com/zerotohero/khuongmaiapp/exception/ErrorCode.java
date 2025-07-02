package com.zerotohero.khuongmaiapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ROLE_EXISTED(1001,"Đã tồn tại role này", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTED(1001,"Đã tồn tại permission này", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_EXISTED(1001,"Đã tồn tại danh mục này", HttpStatus.BAD_REQUEST),
    STATUS_NAME_EXISTED(1001,"Đã tồn tại TRẠNG THÁI này", HttpStatus.BAD_REQUEST),
    DEPARTMENT_EXISTED(1002,"Đã tồn tại trụ sở này", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(999,"Lỗi 500, team backend nên xem lại logic",HttpStatus.INTERNAL_SERVER_ERROR),
    ERRORCODE_INVALID(998,"Điền sai errorcode rồi bạn",HttpStatus.BAD_REQUEST),
    ROLE_MIN_INVALID(997,"Số lượng ko được dưới 3 phần tử",HttpStatus.BAD_REQUEST),
    DEPARTMENT_IS_NOT_EXISTED(996,"Không tồn tại trụ sở này",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(1111,"kHÔNG TỒN TẠI NHÂN VIÊN NÀY",HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1111,"kHÔNG TỒN TẠI QUYỀN NÀY",HttpStatus.BAD_REQUEST),
    UNIQUE_ACCOUNT(1111,"Mỗi nhân viên chỉ được 1 tài khoản",HttpStatus.BAD_REQUEST),
    UNIQUE_USERNAME(1111,"Username đã tồn tại",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1111,"kHÔNG TỒN TẠI USER NÀY",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(2222,"Category not found",HttpStatus.NOT_FOUND),
    FILE_TOO_LARGE(1003,"File quas kích thước",HttpStatus.BAD_REQUEST),
    FILE_EXTENSION_INVALID(1004,"Đuôi file không hợp lệ",HttpStatus.BAD_REQUEST),
    THIS_IS_NOT_IMAGE(1005,"Đây không phải là file ảnh",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1234,"Không tồn tại sản phẩm",HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_FOUND(1234,"Không tồn tại khách hàng",HttpStatus.NOT_FOUND),
    WAREHOUSE_NOT_FOUND(1234,"Nhà kho không tồn tại",HttpStatus.NOT_FOUND),
    MATERIAL_NOT_FOUND(1234,"Không tồn tại vật liệu này",HttpStatus.NOT_FOUND),
    STATUS_NOT_FOUND(1234,"Không tồn tại trạng thái này",HttpStatus.NOT_FOUND),
    UNAUTHENTICATE(1234,"Đăng nhập không thành công",HttpStatus.UNAUTHORIZED),
    PERMISSION_NOT_FOUND(1234,"Không tìm thấy permission",HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED(1234,"Hết hạn token",HttpStatus.UNAUTHORIZED),
    SOLD_OUT(1234,"Sản phẩm đã hết hàng",HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1234,"Đơn hàng không tìm thấy",HttpStatus.NOT_FOUND),
    NOT_ENOUGH(1234,"Không đủ hàng",HttpStatus.BAD_REQUEST)
    ;


    private int code;
    private String messege;
    private HttpStatusCode statusCode;
}
