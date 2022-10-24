package io.github.susimsek.springbootgraalvmsamples.repository

import io.github.susimsek.springbootgraalvmsamples.domain.Todo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : ReactiveCrudRepository<Todo, String>
