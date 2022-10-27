package io.github.susimsek.springbootgraalvmsamples.client

import io.github.susimsek.springbootgraalvmsamples.client.data.TodoDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

interface TodoClient{

    @GetExchange("/todos/{id}")
    fun getTodo(@PathVariable id: Int): Mono<TodoDto>
}
