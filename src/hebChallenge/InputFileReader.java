package hebChallenge;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InputFileReader {

	public String getInputFile()
	{
		String inputPath = "src/hebChallenge/input.txt";
		StringBuilder inputString = new StringBuilder();;
		try {
			//read in file text
			Stream<String> textStream = Files.lines(Paths.get(inputPath));
			
			//build string of file text
			textStream.forEach(i -> inputString.append(i).append("\n"));
		}
		catch(IOException exception)
		{
			exception.printStackTrace();
		}
		
		return inputString.toString();
	}
	
}
