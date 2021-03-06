
//CLASS: RawData - Used by Setup and DriverApp 
//AUTHOR: Blake
//******************************  Assignment 6  ****************************

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RawData {

	String inFileName = "RawDataTest.csv";
	private FileReader input;
	private BufferedReader inFile;
	public int n = 0;
	private String oneLine;

	// Allows you to select a different input file
	public RawData(String inFileName)
			throws FileNotFoundException, IOException {
		input = new FileReader(inFileName);
		inFile = new BufferedReader(input);
	}

	// A single record read in.
	public boolean input1Line() throws IOException {
		String theLine = inFile.readLine();
		if (theLine != null) {
			cleanup(theLine);
			n++;
			return true;
		} else {
			return false;
		}
	}

	// split record into individual fields
	private void cleanup(String theLine) { // This is the line that parses out
											// the fields from each line
		oneLine = theLine;

	}

	public int getN() {

		return n;
	}

	public void finishUp() throws IOException {
		inFile.close();
		input.close();
	}

	public String getLine() {
		return oneLine;
	}

}
