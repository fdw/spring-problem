package com.example.child.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

private object Roles {
    const val ADMIN = "ADMIN"
    const val USER = "USER"
}

@Configuration
@Order(2)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().and()
            .requestMatchers()
                .antMatchers("/**")
                .and()
            .authorizeRequests()
                .anyRequest().hasAnyRole(Roles.ADMIN, Roles.USER)
    }
}
