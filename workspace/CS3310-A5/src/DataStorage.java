import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/***********************************
 * Manages the FakeActualData files. stored in a binary file TEST MODE World
 * Data App 2.5
 * 
 * @author Caleb Viola
 */
public class DataStorage {
	private RandomAccessFile file;
	private int byteOffset;

	/*******************************************************
	 * Initializes the binary file.
	 * 
	 * @param tL
	 * @param fileName
	 * @throws IOException
	 */
	public DataStorage(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "r");

	}

	// Reads whole file for testing

	public void readFile() throws IOException {
		file.seek(0);
		while (file.getFilePointer() < file.length()) {

			for (int i = 0; i < 2; i++) {
				System.out.print((char)file.readByte());
			}
			System.out.print((char)file.readByte());
			for (int i = 0; i < 3; i++) {
				System.out.print((char)file.readByte());
			}
			System.out.print((char)file.readByte());
			for (int i = 0; i < 17; i++) {
				System.out.print((char)file.readByte());
			}
				System.out.println();
				file.seek(file.getFilePointer()+1);
		}
	}
	
	
	public void finishUp() throws IOException {
		file.close();
	}
}

	/*********************************************
	 * Locates element in txt file by country DRP.
	 * 
	 * @param DRP
	 *            Element id to locate
	 * @param tl
	 *            TheLog object
	 * @throws IOException
	 */
	// public void selectByDRP(int DRP) throws IOException{
	// if (DRP != -1){
	// String line = "";
	// byteOffset(DRP);
	// for (int i = 0; i < 23; i++)
	// line += (char)file.readByte();
	// tl.printf(" -->> %s\t", line);
	// }
	// }
	//
	// /***************************************************
	// * For calculating byteOffset and seeking it.
	// * @param DRP
	// * @return spot status.
	// * @throws IOException
	// */
	// private void byteOffset(int DRP) throws IOException{
	// byteOffset = (DRP-1) * 25;
	// file.seek(byteOffset);
	//
	// }

	/*****************************************
	 * Closes binary file.
	 * 
	 * @throws IOException
	 */
