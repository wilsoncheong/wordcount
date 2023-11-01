package com.adp.wordcount.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class WordCountIntegrationTests {
    
    @Test
	void testWordCountTestingIntegration() {

        assertEquals("a", "a");
    }

}
