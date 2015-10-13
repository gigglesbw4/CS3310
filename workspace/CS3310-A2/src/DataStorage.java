import java.awt.event.FocusAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Formatter;

public class DataStorage {
	private short n;
	private short maxId = 0;
	RandomAccessFile file;

	public DataStorage(String fileName, UIoutput output) throws IOException {

		file = new RandomAccessFile(fileName, "rw");

	}

	// Truncates the file for setup
	public void setup() throws IOException {
		file.setLength(0);
	}

	// Reads the maxID in the file for UserApp
	public void setupApp() throws IOException {
		file.seek(2);
		maxId = file.readShort();
	}

	public void finishUp() throws IOException {
		file.seek(2);
		file.writeShort((short) maxId);
		file.close();

	}

	public void getMax(short id) throws IOException {
		if (maxId <= id) {
			if (maxId == 0) {
				file.seek(4);
			} else {
				file.seek(((maxId * 52) + 4) - 52);
			}

			int x = 0;
			char poo = 0;
			while (x < (52 * (id - maxId))) {

				file.write((char) poo);
				x = x + 1;
			}

			maxId = id;
		}

	}

	public void insert1Country(String code, short id, String name, String continent, int area, long population,
			float lifeExpectancy) throws IOException {

		getMax(id);
		file.seek(((id * 52) + 4) - 52);

		file.writeShort((short) id);
		file.write(code.getBytes());
		while (name.length() < 18) {
			name = name + " ";
		}
		name = name.substring(0, 18);
		while (continent.length() < 13) {
			continent = continent + " ";
		}
		continent = continent.substring(0, 13);
		file.write(name.getBytes());
		file.write(continent.getBytes());
		file.writeInt(area);
		file.writeLong(population);
		file.writeFloat((float) lifeExpectancy);

	}

	public void insert1Country(String code, short id, String name, String continent, int area, long population,
			float lifeExpectancy, UIoutput output) throws IOException {

		output.displayThis("I " + new String(code) + "," + id + "," + new String(name.trim()) + ","
				+ new String(continent.trim()) + "," + area + "," + population + "," + lifeExpectancy);

		if (id > 0 && id < maxId) {
			file.seek(((id * 52) + 4) - 52);
			if (id == file.readShort()) {
				output.displayThis("   >> (duplicate) invalid country id \n");
			} 
		}else if (id > 0 && id < 60) {

				getMax(id);

				file.seek(((id * 52) + 4) - 52);

				file.writeShort((short) id);
				file.write(code.getBytes());
				while (name.length() < 18) {
					name = name + " ";
				}
				name = name.substring(0, 18);
				while (continent.length() < 13) {
					continent = continent + " ";
				}
				continent = continent.substring(0, 13);
				file.write(name.getBytes());
				file.write(continent.getBytes());
				file.writeInt(area);
				file.writeLong(population);
				file.writeFloat((float) lifeExpectancy);
				output.displayThis("   >> OK, " + name + " inserted \n");

			} else {
				output.displayThis("   >> invalid country id \n");

			}
		}

	

	public void select(short id, UIoutput output) throws IOException {

		int x = 0;
		short myID;
		String myCode = "";
		String myName = "";
		String myContinent = "";
		int myArea;
		long myPopulation;
		float myLifeExpectancy;
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);

		if (id > 0 && id < 39) {
			file.seek(((id * 52) + 4) - 52);
			myID = file.readShort();

			if (id == myID) {
				while (x < 34) {
					if (x < 3) {
						myCode = myCode + (char) file.read();
					} else if (x < 21) {
						myName = myName + (char) file.read();
					} else {
						myContinent = myContinent + (char) file.read();
					}
					x = x + 1;

				}
				myArea = file.readInt();
				myPopulation = file.readLong();
				myLifeExpectancy = file.readFloat();
				formatter.format("%s %03d %-24s %-13s %,11d %,13d %4.1f\n", new String(myCode), id, new String(myName),
						new String(myContinent), myArea, myPopulation, myLifeExpectancy);
				output.displayThis("S " + id + " >> ");
				output.displayThis("CDE ID- NAME-------------------- CONTINENT---- -------AREA ---POPULATION LIFE");
				output.displayThis(buf.toString());
				output.displayThis("");// output a blank line
				formatter.close();
			}
			if (x == 0) {
				output.displayThis("S " + id + " >> " + "invalid country id " + "\n");
			}
		}

	}

	public void delete(short id, UIoutput output) throws IOException {

		int x = 0;
		short myID;
		String myCode = "";
		String myName = "";
		String myContitnent = "";

		if (id > 0 && id < 39) {
			file.seek(((id * 52) + 4) - 52);
			myID = file.readShort();

			if (id == myID) {
				while (x < 34) {
					if (x < 3) {
						myCode = myCode + (char) file.read();
					} else if (x < 21) {
						myName = myName + (char) file.read();
					} else {
						myContitnent = myContitnent + (char) file.read();
					}
					x = x + 1;

				}
				file.readInt();
				file.readLong();
				file.readFloat();
				output.displayThis("D " + id + " >> OK, " + myName.replaceAll("\\s", "") + " Deleted\n");

				file.seek(((id * 52) + 4) - 52);
				int poo = 0;
				x = 0;
				while (x < (52)) {
					file.write((char) poo);
					x = x + 1;
				}

				file.seek(((id * 52) + 4) - 52);
				myID = file.readShort();

			}
		}
		if (x == 0) {
			output.displayThis("D " + id + " >> " + "invalid country id " + "\n");
		}

	}

}
