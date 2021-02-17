package com.example.config

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.actuate.info.InfoEndpoint
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User

private object Roles {
    const val ADMIN = "ADMIN"
    const val USER = "USER"
    const val INFRA = "INFRA"
}

@Configuration
@Order(1)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .cors().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .httpBasic().and()
            .requestMatchers().antMatchers("/actuator/**").and()
            .authorizeRequests()
            .requestMatchers(EndpointRequest.to(InfoEndpoint::class.java)).hasAnyRole(Roles.ADMIN, Roles.INFRA)
            .requestMatchers(EndpointRequest.to(PrometheusScrapeEndpoint::class.java)).hasAnyRole(Roles.ADMIN, Roles.INFRA)
            .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole(Roles.ADMIN)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser(
                User.builder().username("admin")
                    .password("verysecretpassword")
                    .roles(Roles.ADMIN)
                    .build()
            )
            .withUser(
                User.builder().username("infra")
                    .password("widelyknownpassword")
                    .roles(Roles.INFRA)
                    .build()
            )
            .withUser(
                User.builder().username("user")
                    .password("whatsapassword")
                    .roles(Roles.USER)
                    .build()
            )
    }
}
