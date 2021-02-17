package com.example.child.config

import com.example.child.properties.AnimalProperties
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc


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
}
