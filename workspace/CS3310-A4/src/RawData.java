//CLASS: RawData - Used by Setup program
//AUTHOR: Blake
//************************************  Assignment 3  **********************************


import java.io.*;

public class RawData 
{
	
	String inFileName = "LineAt6Am.csv";
	private FileReader input;
	private BufferedReader inFile;
	public int n = 0;
	private String code;
	private short id;


	
	public RawData(String inFileName) throws FileNotFoundException, IOException
	{
		input = new FileReader(inFileName);
		inFile = new BufferedReader(input);
	}	
	
	//A single record read in.
	public boolean input1Country() throws IOException
	{
		String theLine = inFile.readLine();
		if(theLine != null)
		{
			cleanup(theLine);
			n++;
			return true;
		}	
		else
			return false;
	}
	
	//split record into individual fields
	private void cleanup(String theLine)
	{			// This is the line that parses out the fields from each line	
		
		
	//	String field[] = theLine.split(",");

		code = theLine;

		
	}
	
	public int getN()
	{

		return n;
	}
	
	public short getId()
	{
		return id;
	}
	
		
	public void finishUp() throws IOException
	{
		inFile.close();
		input.close();
	}
	
	
	public String getCode(){
		return code;
	}
	
}

	
	
	