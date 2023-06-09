package org.jsanz.front.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {"org.jsanz.front.view","org.jsanz.front.controller","org.jsanz.front.view"})
public class FrontConfig {
	
    @Bean
    public MessageSource messageSource () {
        ResourceBundleMessageSource messageSource =
                            new ResourceBundleMessageSource();
        messageSource.setBasename("mensaje/Mensaje");
        return messageSource;
    }

}
