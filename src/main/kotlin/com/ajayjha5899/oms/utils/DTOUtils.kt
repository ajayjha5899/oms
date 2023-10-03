package com.ajayjha5899.oms.utils

import com.ajayjha5899.oms.dto.MetaData
import com.ajayjha5899.oms.enum.ResponseMessageCodeEnums
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DTOUtils {
    fun getSuccessMetaData(message: String? = ResponseMessageCodeEnums.OK.message): MetaData = MetaData(
            timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
            message = message ?: ResponseMessageCodeEnums.OK.message,
            code = ResponseMessageCodeEnums.OK.code
    )

    fun getBadRequestMetaData(message: String? = ResponseMessageCodeEnums.BAD_REQUEST.message): MetaData = MetaData(
        timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
        message = message ?: ResponseMessageCodeEnums.BAD_REQUEST.message,
        code = ResponseMessageCodeEnums.BAD_REQUEST.code
    )

    fun getInternalServerErrorMetaData(message: String? = ResponseMessageCodeEnums.BAD_REQUEST.message): MetaData  = MetaData(
        timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
        message = message ?: ResponseMessageCodeEnums.INTERNAL_SERVER_ERROR.message,
        code = ResponseMessageCodeEnums.INTERNAL_SERVER_ERROR.code
    )
}