package io.github.susimsek.springbootgraalvmsamples.cron

import io.github.susimsek.springbootgraalvmsamples.client.TodoClient
import io.github.susimsek.springbootgraalvmsamples.domain.Todo
import io.github.susimsek.springbootgraalvmsamples.repository.TodoRepository
import io.github.susimsek.springbootgraalvmsamples.service.SequenceGeneratorService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TodoCronTask(
    private val todoClient: TodoClient,
    private val todoRepository: TodoRepository
) {
    @Scheduled(fixedDelay = 5000)
    fun hello() {
        todoClient.getTodo(1)
            .flatMap { todoRepository.save(Todo(
                id = it.id,
                userId = it.userId,
                title = it.title
            )) }
            .doOnSuccess {println("saved todo : $it") }
            .subscribe()
    }
}
