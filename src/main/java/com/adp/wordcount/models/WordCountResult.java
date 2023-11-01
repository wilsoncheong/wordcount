package com.adp.wordcount.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* WordCount Result
* This is Model to return after process wordcount
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCountResult {
    
    /**
     * This is Map to store the wordcount results
     */
    private Map<String, Integer> wordCounts;
    
}
