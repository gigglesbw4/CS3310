import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



public class DataStorage {
	private short n;
	private short maxId;
	RandomAccessFile file;
	


	public DataStorage(String fileName,UIoutput output) throws IOException{
		
		file = new RandomAccessFile(fileName, "rw");
		file.seek(2);
		file.writeShort((short)39);	
		int x=0;
		int poo=0;
		file.seek(4);
		while(x<(52*39)){
		file.write((char)poo);
		x = x+1;
		}

	
		
		
		
	}
	public void insert1Country(String code, short id, String name,
			String continent, int area, long population, float lifeExpectancy) throws IOException{
		
		
		
		
		
		
		
		file.seek(((id*52)+4)-52);
		
		file.writeShort((short)id);	
		file.write(code.getBytes());
		while (name.length()<18){name = name+" ";}
		name = name.substring(0,18);
		while (continent.length()<13){continent = continent+" ";}
	    continent = continent.substring(0,13);
		file.write(name.getBytes());
		file.write(continent.getBytes());
		file.writeInt(area);
		file.writeLong(population);
		file.writeFloat((float)lifeExpectancy);
		
		
		
	}
	
	
	public void finishUp() throws IOException {
		file.close();
		
	}
	
	
	
	
}
//	public void insert1Country(String code, Short id, String name, String continent, int area, long population,
//			float lifeExpectancy, UIoutput output) throws IOException {
//		file.writeShort((short)12);	
//		file.write("MEX".getBytes());
//		while (name.length()<18){
//			name = name+" ";
//		}
//		file.write("Mexico            ".getBytes());
//		file.write(name.getBytes());
//		file.write("North America".getBytes());
//		file.writeInt(1958201);
//		file.writeLong(98881000);
//		file.writeFloat((float)71.5);
//		
//	}
//	public DataStorage(String fileName, Log log) throws IOException{
//		file = new RandomAccessFile(fileName, "rw");
//		file.seek(4);
//		try {
//			String[] line = file.readLine().split("'");
//			n = Short.parseShort(line[0]);
//			maxId = Short.parseShort(line[1]);
//		} catch (Exception e) {
//			n = 0;
//			maxId = 1;
//			file.writeBytes(String.format("%03d'%03d\n", n, maxId));
//		}
//		log.displayThis("FILE STATUS > CountryData FILE opened");
//		dtr = new DataTableRecord();
//	}	
//	
//
//	/************************************************************
//	 * IN command
//	 * @param code
//	 * @param id
//	 * @param name
//	 * @param continent
//	 * @param area
//	 * @param population
//	 * @param lifeExpectancy
//	 * @param userApp
//	 * @param log
//	 * @throws IOException
//	 */
//	public void insert1Country(String code, short id, String name,
//			String continent, int area, long population, float lifeExpectancy,
//			Log log) throws IOException { 
//		dtr.byteOffset(id);
//		status = dtr.locateWithStatus(file);
//		if ((status[0] && status[1]) || (!status[0] && status[1])){
//			if (id > maxId){
//				/* fill space in between with pre-formatted string
//				 * for better management and readability
//				 * */
//				if (id-maxId != 1){
//					dtr.byteOffset(maxId+1);
//					status = dtr.locateWithStatus(file);
//					for (int x = maxId+1; x < id; x++)
//						dtr.fill1record(file);
//				}
//				maxId = id;
//			}
//			dtr.write1Country(file, code, id, name, continent, area,
//					population, lifeExpectancy);
//			n++;
//			
//		}else if (status[0] && !status[1])
//			log.displayThis("   SORRY, another country has that id");
//		else
//			log.displayThis("   SORRY, invalid id");	
//	}
//}
//	
	
	
	
	
	
	
	

//	private FileWriter output;
//	private BufferedWriter outFile;
//	private FileReader input;
//	private BufferedReader inFile;
//	private UIoutput uioutput;
//	private Node [] BST;//use static array with the length of 200 as the interior storage of BST
//
//	private int rootPtr = -1; //initially, the tree is empty
//	private int n = 0;//the number of nodes on the tree
//	private int nextEmptyPtr = 0; //next empty pointer
//	
//	private int i; //the current pointer
//	private int parentI; //the parent of current pointer
//	private int index; //used for UserApp to load backup.csv to interior storage
//	
//	//Constructor for Setup program.
//	public DataStorage() throws IOException
//	{
//		output = new FileWriter("Backup.csv");
//		outFile = new BufferedWriter(output);
//		BST = new Node[200];
//	}
//	
//	//Overloaded Constructor for UserApp program
//	public DataStorage(FileReader input, UIoutput uioutput) throws IOException
//	{
//		this.input = input;
//		this.uioutput = uioutput;
//		inFile = new BufferedReader(input);
//		BST = new Node[200];
//		index = 0;
//		
//		String newLine = inFile.readLine();
//		String field[] = newLine.split(",");
//		rootPtr = Integer.parseInt(field[0]);
//		n = Integer.parseInt(field[1]);
//		nextEmptyPtr = Integer.parseInt(field[2]);
//		
//		while((newLine = inFile.readLine()) != null) //load backup.csv to interior storage, which is an array
//		{
//			field = newLine.split(",");
//			BST[index] = new Node(field[2], field[3] + "," + field[4] + "," + field[5] + "," + field[6] + "," + field[7] + "," + field[8], 
//					Integer.parseInt(field[0]), Integer.parseInt(field[1]));
//			index++;
//		}
//	}
//	
//	//insert method to be called by SetUp or UserApp. Insert record from RawData.csv to Backup.csv
//	public void insert(String countryCode, String restOfData) throws IOException
//	{	
//		if(output != null) //SetUp is calling the method!!!!!!!!!!!!!!! Since output is initialized in the constructor for Setup
//		{
//			BST[nextEmptyPtr] = new Node(countryCode, restOfData);;
//			BST[nextEmptyPtr].leftChildPtr = -1;
//			BST[nextEmptyPtr].rightChildPtr = -1;
//			
//			boolean LorR = true;//if L, true; if R, false
//			
//			if(rootPtr == -1)//special case - if the tree is empty
//			{
//				rootPtr = nextEmptyPtr;
//			}
//			else //normal case - if the tree is not empty: 1. compare the record with the root to decide LorR
//			{
//				i = rootPtr;
//				while(i != -1)
//				{
//					parentI = i;
//					if(countryCode.compareTo(BST[parentI].countryCode) < 0)
//					{
//						i = BST[i].leftChildPtr;
//						LorR = true;
//					}	
//					else
//					{
//						i = BST[i].rightChildPtr;
//						LorR = false;
//					}	
//				}
//				if(LorR == true) //hang a left leaf
//				{
//					BST[parentI].leftChildPtr = nextEmptyPtr;
//				}
//				else //hang a right leaf
//				{
//					BST[parentI].rightChildPtr = nextEmptyPtr;
//				}
//			}
//			n++;
//			nextEmptyPtr++;
//		}
//		else //UserApp is calling the method!!!!!!!!!! Since output is not initialized in constructor for UserApp
//		{
//			int count = 1;
//			BST[nextEmptyPtr] = new Node(countryCode, restOfData);;
//			BST[nextEmptyPtr].leftChildPtr = -1;
//			BST[nextEmptyPtr].rightChildPtr = -1;
//			
//			boolean LorR = true;//if L, true; if R, false
//			
//			if(rootPtr == -1)//special case - if the tree is empty
//			{
//				rootPtr = nextEmptyPtr;
//				uioutput.displayThis("I " + BST[nextEmptyPtr].countryCode + "," + BST[nextEmptyPtr].restOfData);
//				uioutput.displayThis("OK, " + BST[nextEmptyPtr].name + " inserted [visited " + count + " nodes]\n");
//			}
//			else //normal case - if the tree is not empty: 1. compare the record with the root to decide LorR
//			{
//				i = rootPtr;
//				while(i != -1)
//				{
//					parentI = i;
//					if(countryCode.compareTo(BST[parentI].countryCode) < 0)
//					{
//						i = BST[i].leftChildPtr;
//						LorR = true;
//					}	
//					else
//					{
//						i = BST[i].rightChildPtr;
//						LorR = false;
//					}	
//					count++;
//				}
//				if(LorR == true) //Hang a left leaf
//				{
//					BST[parentI].leftChildPtr = nextEmptyPtr;
//					uioutput.displayThis("I " + BST[nextEmptyPtr].countryCode + "," + BST[nextEmptyPtr].restOfData);
//					uioutput.displayThis("OK, " + BST[nextEmptyPtr].name + " inserted [visited " + count + " nodes]\n");
//				}
//				else //hang a right leaf
//				{
//					BST[parentI].rightChildPtr = nextEmptyPtr;
//					uioutput.displayThis("I " + BST[nextEmptyPtr].countryCode + "," + BST[nextEmptyPtr].restOfData);
//					uioutput.displayThis("OK, " + BST[nextEmptyPtr].name + " inserted [visited " + count + " nodes]\n");
//				}
//			}
//			n++;
//			nextEmptyPtr++;
//		}
//	}
//	
//	public void delete(String countryCode) throws IOException
//	{
//		//search whether or not the record exists. If so, change id to 0; else, display "invalid" message
//		int count = 1;
//		i = rootPtr; //start search from the root
//		while((i != -1) && (!(countryCode.equals(BST[i].countryCode)))) //traverse until find or the end of the tree
//		{
//			count++;
//			if(countryCode.compareTo(BST[i].countryCode) < 0)
//				i = BST[i].leftChildPtr;
//			else
//				i = BST[i].rightChildPtr;
//		}
//		if(i == -1) //the record does not exist
//		{
//			uioutput.displayThis("D " + countryCode + " >> " + "invalid country code " + "[visited " + count + " nodes]" + "\n");
//		}
//		else //successfully find
//		{
//			if(BST[i].id.equals("000")) //the record has already been deleted
//			{
//				uioutput.displayThis("D " + countryCode + " >> " + "invalid country code " + "[visited " + count + " nodes]" + "\n");
//			}
//			else //the record has not been deleted
//			{
//				BST[i].id = "000";//tomstone the node
//				uioutput.displayThis("D " + countryCode + " >> " + "OK, " + BST[i].name + " deleted " + "[visited " + count + " nodes]" + "\n");
//			}	
//		}
//	}
//	
//	public void select(String countryCode) throws IOException
//	{
//		//search if the record exists. if so, output to Log.txt; else, display "invalid" message
//		int count = 1;
//		i = rootPtr; //start search from the root
//		while((i != -1) && (!(countryCode.equals(BST[i].countryCode)))) //traverse until find or the end of the tree
//		{
//			if(countryCode.compareTo(BST[i].countryCode) < 0)
//				i = BST[i].leftChildPtr;
//			else
//				i = BST[i].rightChildPtr;
//			count++;
//		}
//		if(i == -1) //the deepest tree leaf, not found
//		{
//			uioutput.displayThis("S " + countryCode + " >> " + "invalid country code " + "[visited " + count + " nodes]" + "\n");
//		}
//		else //successfully found
//		{
//			if((BST[i].id.equals("000"))) //the record has been deleted already
//			{
//				uioutput.displayThis("S " + countryCode + " >> " + "invalid country code " + "[visited " + count + " nodes]" + "\n");
//			}
//			else //display the information of the record (formatted)
//			{
//				uioutput.displayThis("S " + countryCode + " >> " + "[visited " + count + " nodes]");
//				uioutput.displayThis("CDE ID- NAME-------------------- CONTINENT---- -------AREA ---POPULATION LIFE");
//				uioutput.displayThis(BST[i]);
//				uioutput.displayThis("");//output a blank line
//			}	
//		}
//	}
//	
//	private void inOrderTraversal(int rootPtr) throws IOException
//	{
//		if(rootPtr == -1){}//if the tree is empty, then do nothing
//		else
//		{
//			inOrderTraversal(BST[rootPtr].leftChildPtr); //traverse the left subtree
//			
//			if(BST[rootPtr].id.equals("000")){}//the node is tombstoned, don't display
//			else
//			{
//				uioutput.displayThis(BST[rootPtr]);//visit the node
//			}
//			inOrderTraversal(BST[rootPtr].rightChildPtr);//traverse the right subtree
//		}
//	}
//	
//	public void all() throws IOException //inorder tranversal the tree
//	{
//		uioutput.displayThis("A");
//		uioutput.displayThis("CDE ID- NAME-------------------- CONTINENT---- -------AREA ---POPULATION LIFE");
//		inOrderTraversal(rootPtr);
//		uioutput.displayThis("===========================\n");
//	}
//	
//	public void finishUp() throws IOException
//	{
//		
//		if(output != null) //if the method is called by SetUp, output from interior storage to Backup.csv, and then close all files.
//		{
//			outFile.write(rootPtr + "," + n + "," + nextEmptyPtr + "\n");
//			for(int i = 0; i < n; i++)
//			{
//				outFile.write(BST[i].leftChildPtr + "," + BST[i].rightChildPtr + "," + BST[i].countryCode + "," + BST[i].restOfData + "\n");
//			}
//			outFile.close();
//			output.close();
//		}
//		else //if the method is called by UserApp, save the altered BST to backup file and close all files
//		{
//			output = new FileWriter("backup.csv");
//			outFile = new BufferedWriter(output);
//			outFile.write(rootPtr + "," + n + "," + nextEmptyPtr + "\n");
//			for(int i = 0; i < n; i++)
//			{
//				outFile.write(BST[i].leftChildPtr + "," + BST[i].rightChildPtr + "," + BST[i].countryCode + "," + BST[i].restOfData + "\n");
//			}
//			
//			inFile.close();
//			input.close();
//			outFile.close();
//			output.close();
//		}
//	}
//}
