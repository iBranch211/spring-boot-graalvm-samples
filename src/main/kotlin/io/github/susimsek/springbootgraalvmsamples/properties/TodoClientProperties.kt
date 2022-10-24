package io.github.susimsek.springbootgraalvmsamples.properties

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated


@ConfigurationProperties(prefix = "todo-client")
@Validated
data class TodoClientProperties(
    @field:NotBlank
    var baseUrl: String
)