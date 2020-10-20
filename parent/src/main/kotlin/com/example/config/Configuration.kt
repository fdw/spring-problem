package com.example.config

import com.example.child.ChildContextBuilder
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import javax.servlet.ServletContext

@Configuration
class Configuration(private val applicationContext: ApplicationContext) : ServletContextInitializer {
    override fun onStartup(servletContext: ServletContext) {
        listOf("lizard", "cat").forEach {
            registerAnimalAdapter(it, servletContext)
        }
    }

    private fun registerAnimalAdapter(
        animal: String,
        servletContext: ServletContext
    ) {
        ChildContextBuilder(animal)
            .registerEndpoints(applicationContext, servletContext)
    }
}
