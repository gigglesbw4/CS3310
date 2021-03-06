
//CLASS: Log
//AUTHOR: Blake Wrege 
//DESCRIPTION: Reused from UIoutput, outputs lines to Log.txt
//******************************  Assignment 6  ****************************

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class Log {
	private Formatter output;

	public Log() throws IOException // Constructor for Setup
	{
		output = new Formatter(
				new BufferedWriter(new FileWriter("Log.txt", false)));
	}

	public Log(String message) throws IOException {
		output = new Formatter(
				new BufferedWriter(new FileWriter("Log.txt", true)));
	}

	// Receive status messages.
	public void displayThis(String status) throws IOException {
		output.format(status, null);
	}

	public void displayBrk() throws IOException {
		output.format("#   #   #   #   #   #   #   #   #   #   #   #" + "\n",
				null);
	}

	public void finishUp() throws IOException {
		output.close();
	}

	public void displayThis(StringBuffer buf) {
		// TODO Auto-generated method stub

	}
}
