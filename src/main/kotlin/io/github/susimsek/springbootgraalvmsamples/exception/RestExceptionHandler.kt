package io.github.susimsek.springbootgraalvmsamples.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    // 400
    override fun handleWebExchangeBindException(ex: WebExchangeBindException,
                                                          headers: HttpHeaders,
                                                          status: HttpStatusCode,
                                                          exchange: ServerWebExchange) :Mono<ResponseEntity<Any>> {
        val errorMap = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return just(ResponseEntity(errorMap, headers, HttpStatus.BAD_REQUEST))
    }
}