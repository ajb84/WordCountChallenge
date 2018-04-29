package hebChallenge;


public class InputOutputGenerator {

	public static void main(String[] args) {
		
		//Creating input and output objects
		InputFileReader inputFile = new InputFileReader();
		OutputFileGenerator outputFile = new OutputFileGenerator();
		
		//Generating output file
		outputFile.generateOutputFile(inputFile.getInputFile());

	}
}
