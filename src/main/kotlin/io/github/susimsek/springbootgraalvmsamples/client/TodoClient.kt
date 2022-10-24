package io.github.susimsek.springbootgraalvmsamples.client

import io.github.susimsek.springbootgraalvmsamples.client.data.TodoDto
import io.github.susimsek.springbootgraalvmsamples.exception.ResourceNotFoundException
import io.github.susimsek.springbootgraalvmsamples.properties.TodoClientProperties
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
@EnableConfigurationProperties(TodoClientProperties::class)
@RegisterReflectionForBinding(TodoDto::class)
class TodoClient(
    webClientBuilder: WebClient.Builder,
    todoClientProperties: TodoClientProperties) {

    private lateinit var webClient: WebClient

    init {
        this.webClient = webClientBuilder
            .baseUrl(todoClientProperties.baseUrl)
            .build()
    }

    fun getTodo(id: Int): Mono<TodoDto> {
        return webClient.get()
            .uri("/todos/{id}", id)
            .retrieve()
            .bodyToMono(TodoDto::class.java)
            .onErrorResume{Mono.error(ResourceNotFoundException("Todo", "id", id))}
    }
}
