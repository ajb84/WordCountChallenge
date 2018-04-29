package hebChallenge;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class OutputFileGenerator {

	public void generateOutputFile(String inputString) {
		
		//removing predefined punctuation
		inputString = inputString.replace(",", "");
		inputString = inputString.replace(".", "");
		
		//removing capitalization
		inputString = inputString.toLowerCase();
		
		List<String> wordList = new ArrayList<String>();
			
		//Creating string array for hashmap 
		String[] splitInput = inputString.split("\\s+");
	
		//Creating output file
		Path outputFile = Paths.get("src/hebChallenge/output.txt");
		
		//Getting longest word length
		int longestWordLength = getLongestWordLength(splitInput);
		

		
		//creating HashMap for word count
		Map<String, Integer> wordHash = new HashMap<String, Integer>();
		
		//Iterating through string to count words
		for(int i=0; i < splitInput.length; i++) {
			if(wordHash.containsKey(splitInput[i])) {
				int wordCount = wordHash.get(splitInput[i]);
				wordHash.put(splitInput[i], wordCount+1);
			}
			else {
				wordHash.put(splitInput[i], 1);
			}
		}
		
		//sorting wordHash
		wordHash = wordCountSorter(wordHash);

		
				
		//Write output file
		for(Entry<String, Integer> e : wordHash.entrySet()) {
			
			//building string in correct format
			String writeString = buildWhiteSpace(e.getKey().length(),longestWordLength) + e.getKey() 
								 + " | " + buildEqualSigns(e.getValue()) + " " + e.getValue();
			
			//adding string to list to be written
			wordList.add(writeString);
		}
		
		//writing output file
		try {
			Files.write(outputFile, wordList, Charset.forName("UTF-8"));
		}
		catch (IOException exception) {
			System.err.println(exception);
		}
		
	}

	//method to sort word count in descending order
	private static Map<String, Integer> wordCountSorter(Map<String, Integer> mapToSort){
		 
		//Sort list descending order
		Map<String, Integer> sortedMapList = mapToSort.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey,  Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		return sortedMapList;
		
	}
	
	//finding longest word
	private static int getLongestWordLength(String[] splitInput) {
		String longestWord = "";
		
		for(String word : splitInput) {
			if(word.length() > longestWord.length()) {
				longestWord = word;
			}
		}
		return longestWord.length();
	}
	
	//method to get correct number of equal signs 
	private static String buildEqualSigns(int timesToPrint) {
		StringBuilder builtString = new StringBuilder();
		
		for(int i=0; i < timesToPrint; i++) {
			builtString.append("=");
		}
		
		return builtString.toString();
	}
	
	//method to build white space for formatting
	private static String buildWhiteSpace(int wordLength, int longestWord){
		StringBuilder whiteSpace = new StringBuilder();
		
		if(longestWord > wordLength) {
			int whiteSpaceNeeded = longestWord - wordLength;
			for(int i=0; i < whiteSpaceNeeded; i++) {
				whiteSpace.append(" ");
			}
		}
	
		return whiteSpace.toString();	
		
	}
}
