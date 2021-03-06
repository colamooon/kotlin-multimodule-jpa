package com.colamooon.shop.library.exception

open class ApplicationException(val errorMessageCode: ErrorMessageCode) : RuntimeException(errorMessageCode.message)

class NotFoundException : ApplicationException(ErrorMessageCode.NOT_FOUND)
class LogicalException : ApplicationException(ErrorMessageCode.LOGIC_ERROR)

class SignupException(errorMessageCode: ErrorMessageCode) : ApplicationException(errorMessageCode)
class SigninException(errorMessageCode: ErrorMessageCode) : ApplicationException(errorMessageCode)
