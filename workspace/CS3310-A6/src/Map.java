
//CLASS: Map
//AUTHOR: Blake Wrege 
//DESCRIPTION: Reused from UIoutput, outputs lines to Log.txt
//************************************  Assignment 6  **********************************

import java.io.IOException;
import java.io.RandomAccessFile;

public class Map {
	private int N;
	private RandomAccessFile cityNameListFile;
	private RandomAccessFile mapGraphFile;

	public Map() throws IOException {
		this.N = 0;
		this.mapGraphFile = new RandomAccessFile("MapGraph.bin", "rws");
		this.cityNameListFile = new RandomAccessFile("CityNameList.csv", "rws");
		this.mapGraphFile.seek(0);
		this.N = mapGraphFile.readShort();

	}

	public short getCityNumber(String cityName) throws IOException {
		for (short i = 0; i < N; i++) {
			String nameInList = getCityName(i).trim();
			if (nameInList.compareToIgnoreCase(cityName) == 0) {
				return i;
			}
		}
		return -1;
	}

	public void finishUp() throws IOException {
		this.cityNameListFile.close();
		this.mapGraphFile.close();
	}

	public String getCityCode(short cityNumber) throws IOException {
		cityNameListFile.seek(cityNumber * 16);
		return cityNameListFile.readLine().split(",")[1];
	}

	public short getRoadDistance(short cityNumber1, short cityNumber2) throws IOException {
		if (cityNumber1 == cityNumber2) {
			return 0;
		} else {
			int headerOffset = 2;
			int row = Math.max(cityNumber1, cityNumber2);
			int col = Math.min(cityNumber1, cityNumber2);
			int actualIndex = ((row - 1) * (row)) / 2 + col;
			int offset = headerOffset + actualIndex * 2;
			mapGraphFile.seek(offset);
			return mapGraphFile.readShort();
		}
	}

	public String getCityName(short cityNumber) throws IOException {
		cityNameListFile.seek(cityNumber * 16);
		return cityNameListFile.readLine().split(",")[0];
	}

	public short getN() {
		return (short) this.N;
	}

}
