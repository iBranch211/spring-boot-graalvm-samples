package io.github.susimsek.springbootgraalvmsamples.client.data

import com.fasterxml.jackson.annotation.JsonProperty

data class TodoDto(

	@field:JsonProperty("id")
	val id: Int,

	@field:JsonProperty("completed")
	val completed: Boolean,

	@field:JsonProperty("title")
	val title: String,

	@field:JsonProperty("userId")
	val userId: Int
)
