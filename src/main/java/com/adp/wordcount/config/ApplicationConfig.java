package com.adp.wordcount.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    
    private String processFolder;
    
    private String outputFolder;

}
