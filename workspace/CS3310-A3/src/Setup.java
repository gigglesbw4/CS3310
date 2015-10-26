//PROGRAM: Setup
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage file

//******************************************  Assignment 2  *******************************************************


import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		
		
		RawData input = new RawData("RawDataTest.csv");
		UIoutput output  = new UIoutput(); 
		HashTable stor = new HashTable();		
		int count = 0;



		
		while(input.input1Country()) //loop through the end of the file
		{

			
			
			stor.insert1Country(input.getCode(), input.getId(),count);
		count = count +1;

		}
		input.finishUp();

		output.displayBrk();
		output.displayThis("CASE:	1");
		output.displayThis("RAW DATA FILE:	Test");
		output.displayThis("HASH FUNCTION: 1 (with maxNHomeLoc: 20)");
		output.displayThis("COL RESOL ALG: 2 (Chaining, Separate)");

		stor.Hash(count,1, 20, 2, output);
		output.displayBrk();
		
		
		
		
		
		
		
		
		
		
		
//		RawData inputAll = new RawData("RawDataAll.csv");
//		HashTable storAll = new HashTable();	
//		count = 0;
//		System.out.println("now for all");
//		
//		while(inputAll.input1Country()) //loop through the end of the file
//		{
//			
//			
//			storAll.insert1Country(inputAll.getCode(), inputAll.getId(),count);
//		count = count +1;
//
//		}
//		inputAll.finishUp();
//		storAll.ReadAll(count);
//		
		
		
		
		
		
		
		
		

		output.finishUp();

	}
}



////setup ensures that the binary file has been cleared
//stor.setup();
//
//output.displayThis("-->> SETUP started");
//output.displayThis("-->> OPENED RawData file");

//output.displayThis("-->> CLOSED Log file");
//output.displayThis("-->> CLOSED RawData file");		
//output.displayThis("-->> CLOSED DataStorage file");
//output.displayThis("-->> SETUP finished" + " - inserted " + input.getN() + " countries into DataStorage\n");
//stor.finishUp();
		
		
