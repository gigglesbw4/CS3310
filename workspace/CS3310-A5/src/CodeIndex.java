
//CLASS: RawData - Used by Setup program
//AUTHOR: Blake
//*****************************************************************************************************
import java.awt.event.FocusAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Formatter;

public class CodeIndex {
	private short M = 0;
	private short RootPtr = 0;
	private short N = 0;
	RandomAccessFile file;
	private short[] TP;
	private String[] KV;
	private short[] DRP;
	private int nodesRead;
	private int sizeOfDataRec;
	private int byteOffset;

	public CodeIndex(String fileName) throws IOException {

		file = new RandomAccessFile(fileName, "r");

	}

	// Reads whole file for testing
	public void readFile() throws IOException {
		file.seek(6);
		while (file.getFilePointer() < file.length()) {
		for (int j = 0; j < M-1; j++) {

			System.out.print(file.readShort());
			for (int i = 0; i < 3; i++) {
				System.out.print((char) file.readByte());
			}
			System.out.print(file.readShort());

		}
		System.out.print(file.readShort());
		System.out.println();
		}
	}

	// Reads the Header Record
	public void setupApp() throws IOException {
		file.seek(0);
		M = file.readShort();
		RootPtr = file.readShort();
		N = file.readShort();
		sizeOfDataRec = M + 3 * (M - 1) + (M - 1);
	}

	private void readOneNode() throws IOException {
		nodesRead++;
		for (int i = 0; i < M - 1; i++)
			KV[i] = "";

		for (int i = 0; i < M; i++)
			TP[i] = file.readByte();
		for (int i = 0; i < M - 1; i++)
			for (int j = 0; j < 3; j++)
				KV[i] += (char) file.readByte();
		for (int i = 0; i < M - 1; i++)
			DRP[i] = file.readByte();
	}

	private int searchOneNode(int pointer, String code) throws IOException {
		byteOffset(pointer);
		readOneNode();

		for (int i = 0; i < M - 1; i++)
			if (code.compareTo(KV[i]) < 0)
				if (TP[i] != -1)
					return searchOneNode(TP[i], code);
				else
					return -1;
			else if (code.compareTo(KV[i]) == 0)
				return DRP[i];
			else if ((code.compareTo(KV[i]) > 0 && i + 1 == M - 1)
					|| (code.compareTo(KV[i]) > 0 && KV[i + 1].equals("]]]")))
				if (TP[i + 1] != -1)
					return searchOneNode(TP[i + 1], code);
				else
					return -1;
		return -1;
	}

	private void byteOffset(int rootPtr) throws IOException {
		byteOffset = 6 + ((rootPtr - 1) * sizeOfDataRec);
		file.seek(byteOffset);
	}

	// Closes the file
	public void finishUp() throws IOException {

		file.close();

	}

}