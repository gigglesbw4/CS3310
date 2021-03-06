//PROGRAM: Setup
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage file

//******************************************  Assignment 2  *******************************************************


import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		
		
		RawData input = new RawData();
		UIoutput output  = new UIoutput(); 
		DataStorage stor = new DataStorage("DataStorage.bin",output);
		//setup ensures that the binary file has been cleared
		stor.setup();
		
		output.displayThis("-->> SETUP started");
		output.displayThis("-->> OPENED RawData file");
		output.displayThis("-->> OPENED DataStorage file");
		output.displayThis("-->> OPENED Log file\n");

		
		while(input.input1Country()) //loop through the end of the file
		{
			
			stor.insert1Country(input.getCode(), input.getId(), input.getName(),
			input.getContinent(), input.getArea(), input.getPopulation(),
			input.getLifeExpectancy());
		

		}
		
		output.displayThis("-->> CLOSED Log file");
		output.displayThis("-->> CLOSED RawData file");		
		output.displayThis("-->> CLOSED DataStorage file");
		output.displayThis("-->> SETUP finished" + " - inserted " + input.getN() + " countries into DataStorage\n");
		stor.finishUp();
		output.finishUp();

	}
}
		
		
