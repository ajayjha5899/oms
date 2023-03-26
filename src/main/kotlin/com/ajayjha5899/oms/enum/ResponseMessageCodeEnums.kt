package com.ajayjha5899.oms.enum

enum class ResponseMessageCodeEnums(val message: String, val code: Int) {
    OK("Success", 200),
    BAD_REQUEST("Bad Request", 400),
    INTERNAL_SERVER_ERROR("Internal Server Error", 500);
}