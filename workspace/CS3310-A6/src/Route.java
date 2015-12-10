
//CLASS: Route
//AUTHOR: Blake Wrege 
//DESCRIPTION: This was useful: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
//************************************  Assignment 6  **********************************

import java.io.IOException;
import java.util.*;

public class Route {
	private short[] distanceArr;
	private short[] prevNodes;
	private List<Short> currentTrace;
	private List<Short> unvisitedNodes;

	// Sets up arrays to be used
	private void setupArrays(short N) {
		this.distanceArr = new short[N];
		this.prevNodes = new short[N];
		this.currentTrace = new ArrayList();
		this.unvisitedNodes = new ArrayList();
		// Sets default distance to infinity
		for (short i = 0; i < N; i++) {
			this.unvisitedNodes.add(i);
			this.prevNodes[i] = -1;
			this.distanceArr[i] = Short.MAX_VALUE;
		}

	}

	// Called from DrivingApp to find distance
	public void findShortestPath(short n, short startNum, short endNum, Map map, Log output) throws IOException {
		this.setupArrays(n);
		this.searchPath(startNum, endNum, map);
		this.getResults(startNum, endNum, map, output);
	}

	private void getResults(short startNum, short destinationNum, Map map, Log output) throws IOException {
		// Creates a stack to store path data
		Deque<Short> drivePath = new ArrayDeque();
		short currentNode = destinationNum;

		// loops while still on the path
		boolean revisited = false;
		while (this.prevNodes[currentNode] >= 0 && currentNode != startNum && revisited == false) {
			// Clears stack and exits if cities are revisited
			if (drivePath.contains(Short.valueOf(currentNode))) {
				drivePath.clear();
				revisited = true;
			} else {
				drivePath.push(currentNode);
				currentNode = prevNodes[currentNode];
			}

		}
		drivePath.push(startNum);
		// Checks to make sure a valid distance
		if ((this.distanceArr[destinationNum] >= 0) == false
				|| (this.distanceArr[destinationNum] == Short.MAX_VALUE) == true) {
			output.displayThis("DISTANCE:  ? \n");
		} else {
			output.displayThis("DISTANCE:  " + this.distanceArr[destinationNum] + "\n");
		}

		// Print the path
		output.displayThis("PATH:  ");
		if (drivePath.size() > 0 && this.distanceArr[destinationNum] >= 0
				&& (this.distanceArr[destinationNum] == Short.MAX_VALUE) == false) {
			boolean first = true;
			while (drivePath.size() > 0) {
				if (!first) {
					output.displayThis(" > ");
				}
				output.displayThis(map.getCityName(drivePath.pop()).trim());
				first = false;
			}
		} else {
			output.displayThis("SORRY - can NOT get to destination city from start city");
		}
		output.displayThis("\r\n");

		output.displayThis("TRACE OF TARGETS: ");
		int i = 1;
		for (i = 1; i < currentTrace.size(); i++) {
			output.displayThis(String.format(" %s ", map.getCityCode(currentTrace.get(i))));
		}
		output.displayThis(String.format(" %s\r\n", map.getCityCode(destinationNum)));

		output.displayThis(String.format("# TARGETS: %d \n\n", i));
	}

	private void searchPath(short startNum, short destinationNum, Map map) throws IOException {
		this.distanceArr[startNum] = 0;

		// Loops until all nodes are visited
		while (this.unvisitedNodes.size() > 0) {
			// Set the current node to the currently smallest tentative
			// distance.
			short currentNode = this.unvisitedNodes.get(0);
			for (int nodeI = 0; nodeI < this.unvisitedNodes.size(); nodeI++) {
				if (this.distanceArr[this.unvisitedNodes.get(nodeI)] < this.distanceArr[currentNode]) {
					currentNode = this.unvisitedNodes.get(nodeI);
				}
			}

			// If that node happens to be our destination, we are done.
			if (currentNode == destinationNum) {
				break;
			}

			// The node has been visited.
			if (unvisitedNodes.contains(Short.valueOf(currentNode))) {
				unvisitedNodes.remove(Short.valueOf(currentNode));
				this.currentTrace.add(Short.valueOf(currentNode));
			}

			// For each neighbor of currentNode:
			for (short n = 0; n < map.getN(); n++) { // n = (Possible) Neighbor
				short distance = map.getRoadDistance(currentNode, n);
				// We have a neighbor if there is a distance less than MAX_VALUE
				// ("infinity") AND...
				// We have a neighbor if the node is not the same as the
				// current:
				if (distance < Short.MAX_VALUE && n != currentNode) {
					short alt = (short) (this.distanceArr[currentNode] + distance);

					if (alt < this.distanceArr[n]) {
						this.distanceArr[n] = alt;
						this.prevNodes[n] = currentNode;
					}
				}
			}
		}
	}

}
