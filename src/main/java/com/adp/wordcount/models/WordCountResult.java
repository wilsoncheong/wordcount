package com.adp.wordcount.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCountResult {
    
    private Map<String, Integer> wordCounts;
    
}
