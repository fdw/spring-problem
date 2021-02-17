package com.example.child.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.util.UrlPathHelper

@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        val urlPathHelper = UrlPathHelper()
        urlPathHelper.setAlwaysUseFullPath(false)
        configurer.setUrlPathHelper(urlPathHelper)
    }
}
