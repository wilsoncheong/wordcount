package com.adp.wordcount.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
* WordCount Content
* This is Model to keep the content to process
*
* @author  Wilson Cheong
* @version 1.0
* @since   2023-11-01
*/
public class WordCountContent {
    
    private String content;
}
