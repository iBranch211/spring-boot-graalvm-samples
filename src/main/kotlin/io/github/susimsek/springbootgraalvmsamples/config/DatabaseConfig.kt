package io.github.susimsek.springbootgraalvmsamples.config

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.convert.converter.Converter
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.domain.ReactiveAuditorAware
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import reactor.core.publisher.Mono
import java.time.Clock
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*


@Configuration(proxyBeanMethods = false)
@Import(value = [MongoAutoConfiguration::class, MongoReactiveAutoConfiguration::class])
@EnableReactiveMongoAuditing(
    auditorAwareRef = "fixedAuditor",
    dateTimeProviderRef = "dateTimeProvider"
)
class DatabaseConfig {

    @Bean
    fun mongoCustomConversions() =
        MongoCustomConversions(
            mutableListOf<Converter<*, *>>(
                OffsetDateTimeReadConverter(),
                OffsetDateTimeWriteConverter()
            )
        )

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }

    @Bean
    fun dateTimeProvider(clock: Clock): DateTimeProvider {
        return DateTimeProvider { Optional.of(OffsetDateTime.now(clock)) }
    }

    @Bean
    fun fixedAuditor(): ReactiveAuditorAware<String> {
        return ReactiveAuditorAware { Mono.just("admin") }
    }

    internal class OffsetDateTimeWriteConverter :
        Converter<OffsetDateTime, Date> {
        override fun convert(source: OffsetDateTime): Date {
            return Date.from(source.toInstant())
        }
    }

    internal class OffsetDateTimeReadConverter :
        Converter<Date, OffsetDateTime> {
        override fun convert(source: Date): OffsetDateTime {
            return OffsetDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault())
        }
    }
}
