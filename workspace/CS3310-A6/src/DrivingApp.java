//PROGRAM: DrivingApp
//AUTHOR: Blake Wrege 
//DESCRIPTION: First of all I want to point out that Kaminki's information is somewhat incorrect. There is a under water tunnel between UK and France https://en.wikipedia.org/wiki/Channel_Tunnel 

//************************************  Assignment 6  **********************************

import java.io.IOException;

public class DrivingApp {
	public static void main(String[] args) throws IOException {
		RawData input = new RawData("CityPairs.csv");
		Map map = new Map();
		Route route = new Route();
		Log output = new Log();
		output.displayBrk();

		while (input.input1Line()) // loop through the end of the file
		{
			if (input.getLine().contains("%") == false) {
				String[] cities = input.getLine().split(",");
				short city1Number = map.getCityNumber(cities[0]);
				short city2Number = map.getCityNumber(cities[1]);
				output.displayThis(
						String.format("%s (%d) TO %s (%d) \n", cities[0], city1Number, cities[1], city2Number));

				if (city1Number < 0 || city2Number < 0) {
					output.displayThis("ERROR - one city is NOT on this map \n\n");
				} else {
					route.findShortestPath(map.getN(), city1Number, city2Number, map, output);
				}
				output.displayBrk();
			}
		}
		input.finishUp();
		output.finishUp();

	}
}
