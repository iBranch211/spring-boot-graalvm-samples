package io.github.susimsek.springbootgraalvmsamples.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException

@ControllerAdvice
class RestExceptionHandler {
    // 400

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(ex: WebExchangeBindException) : ProblemDetail {
        val detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Request Content")
        val errorMap = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        detail.setProperty("fieldErrors", errorMap)
        return detail
    }
}