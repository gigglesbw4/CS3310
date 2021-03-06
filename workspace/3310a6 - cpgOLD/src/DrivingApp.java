import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cpg on 12/6/15.
 */
public class DrivingApp {
    public static void main(String[] args) throws IOException {
        Map map = new Map();
        Route route = new Route();
        Log log = new Log("Log.txt");

        BufferedReader reader = new BufferedReader(new FileReader("CityPairs.csv"));

        log.displayDivider();

        String line = null;
        while ((line = reader.readLine()) != null) {
            // Make sure this is not a comment.
            if (!line.startsWith("%")) {
                String[] cities = line.split(",");
                // ask Map's method to getCityNumber for the 2 cities' names (separately)
                short city1Number = map.getCityNumber(cities[0]);
                short city2Number = map.getCityNumber(cities[1]);

                log.displayThisLine(String.format("%s (%d) TO %s (%d)",
                        cities[0],
                        city1Number,
                        cities[1],
                        city2Number));

                // Make sure the cities exist
                if (city1Number < 0 || city2Number < 0) {
                    log.displayThisLine("ERROR - one city is NOT on this map");
                } else {
                    // ask Route's method to findMinCostPath (given above 2 city NUMBERS)
                    route.findMinCostPath(city1Number, city2Number, map.getN(), map, log);
                }

                log.displayDivider();
            }
        }

        reader.close();
        log.finishUp();
    }
}
