package io.github.susimsek.springbootgraalvmsamples.controller.data

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive


data class AddTodoRequest(
    @field:NotBlank
    var title: String,

    @field:Positive
    var userId: Int
)