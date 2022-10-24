package io.github.susimsek.springbootgraalvmsamples.domain

import io.github.susimsek.springbootgraalvmsamples.domain.audit.AbstractUserAuditingEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "database_sequences")
data class DatabaseSequence(

    @Id
    var id: String,

    @Indexed
    var seq: Int,

) : AbstractUserAuditingEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DatabaseSequence) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
