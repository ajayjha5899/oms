package com.ajayjha5899.oms.exception.controller

import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.exception.BadRequestException
import com.ajayjha5899.oms.exception.NullNotAllowedException
import com.ajayjha5899.oms.utils.DTOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    private val logger: Logger = LoggerFactory.getLogger(ExceptionController::class.java)

    @ExceptionHandler
    fun handleBadRequestException(e: BadRequestException): ResponseEntity<ResponseDTO> {
        logger.error("Bad Request: ${e.message}", e)
        val dto = ResponseDTO(meta = DTOUtils.getBadRequestMetaData(e.message))
        return ResponseEntity(dto, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<ResponseDTO> {
        logger.error("Internal Server Error: ${e.message}", e)
        val dto = ResponseDTO(meta = DTOUtils.getInternalServerErrorMetaData(e.message))
        return ResponseEntity(dto, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun handleNullNotAllowedException(e: NullNotAllowedException): ResponseEntity<ResponseDTO> {
        logger.error("Null Not Allowed: ${e.message}", e)
        val dto = ResponseDTO(meta = DTOUtils.getInternalServerErrorMetaData(e.message))
        return ResponseEntity(dto, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}