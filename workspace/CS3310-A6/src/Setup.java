//PROGRAM: Setup
//AUTHOR: Blake Wrege 
//DESCRIPTION: I pretty much just stole the Setup, RawData and UIoutput from A2 and reused them 

//************************************  Assignment 6  **********************************

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String[][] cityNames = new String[0][];
		short[][] adjMatrix = new short[0][];
		int N = 0;
		Short city1, city2, distance;

		RawData input = new RawData("EuropeMapData.csv");
		Log output = new Log();

		int count = 0;

		while (input.input1Line()) // loop through the end of the file
		{
			if (input.getLine().contains("%") == false) {
				if (input.getLine().matches(".*\\d+.*") == false) {
					readCityName(input.getLine(), output);
				} else {
					String[] tokens = input.getLine().split(",");
					if (tokens.length == 2) {
						N = Integer.parseInt(tokens[1]);
						adjMatrix = new short[N][N];
						for (int r = 0; r < N; r++) {
							for (int c = 0; c < N; c++) {
								adjMatrix[r][c] = Short.MAX_VALUE;
							}
						}

					} else {

						city1 = Short.parseShort(tokens[0]);
						city2 = Short.parseShort(tokens[1]);
						distance = Short.parseShort(tokens[2]);

						if (city1 == city2) {
							adjMatrix[city1][city2] = 0;
						} else {
							adjMatrix[Math.max(city1, city2)][Math.min(city1, city2)] = distance;
						}
					}

				}

			}

		}

		// Write map to file.
		RandomAccessFile mapGraphFile = new RandomAccessFile("MapGraph.bin", "rws");
		mapGraphFile.seek(0);
		mapGraphFile.writeShort(N);
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < r; c++) {
				mapGraphFile.writeShort(adjMatrix[r][c]);
			}
		}
		long x = 0;
		mapGraphFile.seek(0);

		mapGraphFile.close();

		input.finishUp();
		output.finishUp();

		// Used Log class to create CityNameList.csv cause I'm lazy
		File file = new File("Log.txt");
		File file2 = new File("CityNameList.csv");
		// Renames Log.txt to CityNameList.csv
		file.renameTo(file2);

	}

	public static void readCityName(String theLine, Log output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);

		String[] tokens = theLine.split(",");
		String name = tokens[0];
		String code = tokens[1];
		formatter.format("%-10s,%-3s \n", name, code);
		output.displayThis(buf.toString());
		formatter.close();
	}
}
