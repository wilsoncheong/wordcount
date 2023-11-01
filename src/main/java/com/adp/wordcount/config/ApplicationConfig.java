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
/**
* Application Config
*
* @author  Wilson Cheong
* @version 1.0
* @since   2023-11-01
*/
public class ApplicationConfig {
    
    private String processFolder;
    
    private String outputFolder;

}
