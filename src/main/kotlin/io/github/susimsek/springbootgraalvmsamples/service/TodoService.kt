package io.github.susimsek.springbootgraalvmsamples.service

import io.github.susimsek.springbootgraalvmsamples.controller.data.AddTodoRequest
import io.github.susimsek.springbootgraalvmsamples.domain.Todo
import io.github.susimsek.springbootgraalvmsamples.repository.TodoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val sequenceGeneratorService: SequenceGeneratorService
) {

    fun createTodo(todo: AddTodoRequest): Mono<Todo> {
        return todoRepository.save(Todo(
            id = sequenceGeneratorService.generateSequence(Todo.SEQUENCE_NAME),
            title = todo.title,
            userId = todo.userId
        ))
    }
}