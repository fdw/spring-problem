package com.example.child

import com.example.child.config.ChildConfiguration
import com.example.child.properties.AnimalProperties
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
import org.springframework.context.ApplicationContext
import javax.servlet.ServletContext

class ChildContextBuilder(private val animal: String) {
    fun registerEndpoints(
        mainApplicationContext: ApplicationContext,
        parentServletContext: ServletContext
    ) {
        val childContext = AnnotationConfigServletWebServerApplicationContext().apply {
            servletContext = parentServletContext
            parent = mainApplicationContext
            registerProperties()
        }
        childContext.refresh()
    }

    private fun AnnotationConfigServletWebServerApplicationContext.registerProperties() {
        register(ChildConfiguration::class.java)

        beanFactory.registerSingleton(
            "animalProperties",
            AnimalProperties(animal)
        )
    }
}
