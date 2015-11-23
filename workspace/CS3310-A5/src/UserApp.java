//PROGRAM: Setup
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage file

//******************************************  Assignment 2  *******************************************************

import java.io.*;

public class UserApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String[] codeIndex = { "CodeIndex1.bin", "CodeIndex2.bin", "CodeIndex3.bin" };
		String[] transData = { "A5TransData1.txt", "A5TransData2.txt", "A5TransData3.txt" };
		String[] dataStorage = { "DataStorage1.txt", "DataStorage2.txt", "DataStorage3.txt" };
		UIoutput output = new UIoutput();
		
		
		for (int i = 0; i < 3; i++) {
			CodeIndex index = new CodeIndex(codeIndex[i]);
			UI process = new UI(transData[i], output);
			DataStorage data = new DataStorage(dataStorage[i]);
			index.setupApp();
	//		index.readFile();
	//		process.readFile();
			data.readFile();
			process.finishUp();
			data.finishUp();
			index.finishUp();
			System.out.println();
		}
		output.finishUp();

	}
}

// file.seek(0);
// int m = file.readShort();
// System.out.print(m);
// System.out.print(file.readShort());
// System.out.print(file.readShort() + "\n");
//
// while (file.getFilePointer() < file.length()) {
// for (int j = 0; j < m-1; j++) {
//
// System.out.print(file.readShort());
// for (int i = 0; i < 3; i++) {
// System.out.print((char) file.readByte());
// }
// System.out.print(file.readShort());
//
// }
// System.out.print(file.readShort());
// System.out.println();
// }
// file.close();
// }
// }