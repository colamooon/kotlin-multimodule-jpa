package com.colamooon.shop.library.exception

enum class ErrorMessageCode(val code: String, val message: String) {
    OK("0000", "ok"),
    DUMMY("9998", "dummy"),
    ERROR("9999", "Internal Server Error"),
    LOGIC_ERROR("9000", "Internal Server Error"),
    NOT_FOUND("9404", "Not Found"),

    SIGNIN_NOT_ALLOWED("1000", "signin not allowed"),
    SNSID_REQUIRED("1001", "snsid required"),
    SNSTYPE_MISMATCHED("1002", "snstype mismatched"),
    SNSTOKEN_NOT_ACCEPTABLE("1003", "snstoken not acceptable"),
    SNSID_ALREADY_EXISTS("1004", "snsid already exists"),
}