//PROGRAM: Setup
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them 

//************************************  Assignment 3  **********************************

import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Tells the RawData Class to use RawDataTest.csv
		RawData input = new RawData("RawDataAll2.csv");
		UIoutput output = new UIoutput();

		int count = 0;

		while (input.input1Country()) // loop through the end of the file
		{
			System.out.println(input.getCode());

		}
		input.finishUp();


	}
}


