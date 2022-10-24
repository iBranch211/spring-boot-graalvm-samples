package io.github.susimsek.springbootgraalvmsamples.controller

import io.github.susimsek.springbootgraalvmsamples.controller.data.AddTodoRequest
import io.github.susimsek.springbootgraalvmsamples.domain.Todo
import io.github.susimsek.springbootgraalvmsamples.properties.TodoClientProperties
import io.github.susimsek.springbootgraalvmsamples.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TodoRestController(
    private val todoClientProperties: TodoClientProperties,
    private val todoService: TodoService
) {
    @GetMapping(value = ["/todo-client/base-url"])
    fun baseUrl(): String {
        return "Todo client base url: ${todoClientProperties.baseUrl}"
    }

    @PostMapping(value = ["/todo"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createTodo(@Valid @RequestBody todo: AddTodoRequest): Mono<Todo> {
        return todoService.createTodo(todo)
    }
}