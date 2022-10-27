package io.github.susimsek.springbootgraalvmsamples.config

import io.github.susimsek.springbootgraalvmsamples.client.TodoClient
import io.github.susimsek.springbootgraalvmsamples.client.data.TodoDto
import io.github.susimsek.springbootgraalvmsamples.properties.TodoClientProperties
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TodoClientProperties::class)
class WebClientConfig {

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean
    @ImportRuntimeHints(WebClientRuntimeHintsRegistrar::class)
    @RegisterReflectionForBinding(TodoDto::class)
    fun todoClient(webClientBuilder: WebClient.Builder,
                   todoClientProperties: TodoClientProperties): TodoClient {
        val webClient  = webClientBuilder
            .baseUrl(todoClientProperties.baseUrl)
            .build()
        val httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return httpServiceProxyFactory.createClient(TodoClient::class.java)
    }
}
