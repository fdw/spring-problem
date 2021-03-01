package com.example.child.config

import com.example.child.properties.AnimalProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.servlet.Filter


@Configuration
@EnableWebMvc
@ComponentScan("com.example.child.resty")
@EnableWebSecurity
@Import(SecurityConfiguration::class, WebConfiguration::class)
class ChildConfiguration(private val animalProperties: AnimalProperties) {
    @Bean
    fun dispatcherServletRegistration(
        applicationContext: ApplicationContext
    ): ServletRegistrationBean<DispatcherServlet> {
        val dispatcherServlet = DispatcherServlet()
        dispatcherServlet.setApplicationContext(applicationContext)
        return ServletRegistrationBean(
            dispatcherServlet,
            "/${animalProperties.animal}/*"
        ).also { it.setName("${animalProperties.animal}Thing") }
    }

    @Bean
    fun springSecurityRegistration(applicationContext: WebApplicationContext, dispatcher: ServletRegistrationBean<DispatcherServlet>): FilterRegistrationBean<DelegatingFilterProxy> {
        val springSecurityFilterChain = DelegatingFilterProxy(DEFAULT_FILTER_NAME, applicationContext)
        val result = FilterRegistrationBean(springSecurityFilterChain, dispatcher)
        result.setName(dispatcher.servletName + DEFAULT_FILTER_NAME)
        return result
    }
}
