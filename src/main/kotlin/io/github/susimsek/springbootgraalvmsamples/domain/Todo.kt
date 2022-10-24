package io.github.susimsek.springbootgraalvmsamples.domain

import io.github.susimsek.springbootgraalvmsamples.domain.audit.AbstractUserAuditingEntity
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "todo")
data class Todo(

    @Id
    var id: Int,

    @Indexed
    var title: String,

    @Indexed
    var userId: Int
) : AbstractUserAuditingEntity() {

    companion object {
        @Transient
        val SEQUENCE_NAME = "todo_sequence"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Todo) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "Todo(id='$id', title='$title', userId='$userId')"
    }
}
