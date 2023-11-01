package com.adp.wordcount.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.adp.wordcount.models.WordCountResult;
import com.adp.wordcount.processors.FileProcessor;
import com.adp.wordcount.processors.MsWordFileProcessor;
import com.adp.wordcount.processors.TextFileProcessor;
import com.adp.wordcount.services.WordCountService;
import com.adp.wordcount.services.WordCountServiceImpl;

@SpringBootTest
@ActiveProfiles("dev")
class WordCountUnitTests {

	WordCountService wordCountService;

	@Mock
	FileProcessor fileProcessor;

    @Test
	void testWordCountResult() throws IOException {

		System.out.println("Start Wordcount");
 
        WordCountService wordCountSvc = new WordCountServiceImpl();

		int expected = 3;
		String content = "a b a b c a";

		WordCountResult result = wordCountSvc.countWordsInContent(content);
		Map<String, Integer> wordCounts = result.getWordCounts();
		
		assertEquals(expected, wordCounts.get("a"));
	}

    @Test
	void testWordCountAscendingOrder() throws IOException {

		System.out.println("Start testWordCountAscendingOrder");
 
        WordCountService wordCountSvc = new WordCountServiceImpl();

		String expected = "c";
		String content = "a b a b c a";

		WordCountResult result = wordCountSvc.countWordsInContent(content);
		Map<String, Integer> wordCounts = wordCountSvc.sortByWordCountAscending(result);
		
		assertEquals(expected, wordCounts.entrySet().stream().findFirst().get().getKey());
	}

    @Test
	void testWordCountDescendingOrder() throws IOException {

		System.out.println("Start testWordCountDescendingOrder");
 
        WordCountService wordCountSvc = new WordCountServiceImpl();

		String expected = "a";
		String content = "a b a b c a";

		WordCountResult result = wordCountSvc.countWordsInContent(content);
		Map<String, Integer> wordCounts = wordCountSvc.sortByWordCountDescending(result);
		
		assertEquals(expected, wordCounts.entrySet().stream().findFirst().get().getKey());
	}

	@Test
	void testTxtFileProcessor() throws IOException {

        fileProcessor = Mockito.mock(TextFileProcessor.class);
		String content = "a b a b c a";
		when(fileProcessor.readFileContent()).thenReturn(content);
		
		String expected = fileProcessor.readFileContent();
		
		assertEquals(expected, content);
	}

	@Test
	void testMsDocFileProcessor() throws IOException {

        fileProcessor = Mockito.mock(MsWordFileProcessor.class);
		String content = "a b a b c a";
		when(fileProcessor.readFileContent()).thenReturn(content);
		
		String expected = fileProcessor.readFileContent();
		
		assertEquals(expected, content);
	}
}
