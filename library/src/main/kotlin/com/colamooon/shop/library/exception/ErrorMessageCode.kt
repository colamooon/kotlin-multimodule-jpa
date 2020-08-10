package com.colamooon.shop.library.exception

enum class ErrorMessageCode(val code: String, val message: String) {
    OK("0000", "ok"),
    DUMMY("9998", "dummy"),
    ERROR("9999", "Internal Server Error"),
    LOGIC_ERROR("9000", "Internal Server Error"),
    NOT_FOUND("9404", "Not Found"),

    SIGNIN_IS_NOT_ALLOWED("1000", "signin is not allowed"),
    SNSID_IS_REQUIRED("1001", "snsid is required"),
    SNSTYPE_IS_MISMATCHED("1002", "snstype is mismatched"),
    SNSTOKEN_IS_NOT_ACCEPTABLE("1003", "snstoken is not acceptable"),
}