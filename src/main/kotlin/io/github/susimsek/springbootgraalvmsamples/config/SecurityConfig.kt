package io.github.susimsek.springbootgraalvmsamples.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers


@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig{
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(passwordEncoder: PasswordEncoder): ReactiveUserDetailsService {
        val user: UserDetails = User.builder().username("user")
            .password(passwordEncoder.encode("password")).roles("User")
            .build()
        val admin: UserDetails = User.builder().username("admin")
            .password(passwordEncoder.encode("password")).roles("ADMIN")
            .build()
        return MapReactiveUserDetailsService(user, admin)
    }



    @Bean
    fun reactiveAuthenticationManager() =
        UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService(passwordEncoder())).apply {
            setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        // @formatter:off
        http
            .securityMatcher(
                NegatedServerWebExchangeMatcher(
                    OrServerWebExchangeMatcher(
                        pathMatchers("/favicon.ico"),
                        pathMatchers(HttpMethod.OPTIONS, "/**")
                    )
                )
            )
            .authenticationManager(reactiveAuthenticationManager())
            .headers()
            .referrerPolicy(ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
            .and()
            .frameOptions().disable()
            .and()
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange()
            .pathMatchers("/actuator/**").permitAll()
            .anyExchange().authenticated()
            .and()
            .httpBasic(withDefaults())
            .formLogin(withDefaults())
        // @formatter:on
        return http.build()
    }
}