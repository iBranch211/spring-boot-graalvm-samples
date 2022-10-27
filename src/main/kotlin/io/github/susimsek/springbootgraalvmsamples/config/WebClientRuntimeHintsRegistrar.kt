package io.github.susimsek.springbootgraalvmsamples.config

import io.github.susimsek.springbootgraalvmsamples.client.TodoClient
import org.springframework.aop.SpringProxy
import org.springframework.aop.framework.Advised
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.core.DecoratingProxy

class WebClientRuntimeHintsRegistrar: RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        hints.proxies().registerJdkProxy(
            TodoClient::class.java,
            SpringProxy::class.java,
            Advised::class.java,
            DecoratingProxy::class.java
        )

    }
}