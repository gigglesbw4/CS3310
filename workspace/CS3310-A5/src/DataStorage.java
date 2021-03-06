
//CLASS: DataStorage
//AUTHOR: Blake
//************************************  Assignment 5  **********************************

import java.io.IOException;
import java.io.RandomAccessFile;

public class DataStorage {
	private RandomAccessFile file;
	private int byteOffset;

	// opens DataStorage file as a random access file
	public DataStorage(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "r");

	}

	// reads one line from DataStorage text file
	public String readData(int loc) throws IOException {
		file.seek((loc - 1) * 25); // seeks to DRP of selected code from
									// transaction data
		String theLine = "";
		for (int i = 0; i < 23; i++) {
			theLine += (char) file.readByte(); // returns selected data as
												// string to UI
		}
		return theLine;

	}

	// closes the data storage file
	public void finishUp() throws IOException {
		file.close();
	}
}
