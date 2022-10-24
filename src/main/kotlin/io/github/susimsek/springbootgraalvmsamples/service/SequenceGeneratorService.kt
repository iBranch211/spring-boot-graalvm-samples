package io.github.susimsek.springbootgraalvmsamples.service

import io.github.susimsek.springbootgraalvmsamples.domain.DatabaseSequence
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class SequenceGeneratorService(
    private val reactiveMongoOperations: ReactiveMongoOperations
) {

    fun generateSequence(sequenceName: String): Int {
        return reactiveMongoOperations.findAndModify(query(where("_id").`is`(sequenceName)),
            Update().inc("seq",1), options().returnNew(true).upsert(true),
            DatabaseSequence::class.java)
            .map { it.seq }
            .switchIfEmpty(Mono.just(1))
            .toFuture().get()
    }
}