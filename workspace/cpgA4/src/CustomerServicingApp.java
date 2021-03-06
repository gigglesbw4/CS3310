import java.io.*;

/**
 * Created by cpg on 11/11/15.
 */
public class CustomerServicingApp {
    public static void main(String[] args) throws IOException {
        BufferedWriter logWriter = new BufferedWriter(new FileWriter("Log.txt"));

        logWriter.write(String.format(">> Program starting.%n", args));

        CustomerPQ pq = new CustomerPQ();

        logWriter.write(String.format("STORE OPENS%n", args));

        logWriter.write(String.format("Will now insert customers from LineAt6Am into PQ.%n", args));

        logWriter.write(String.format("Finished putting customers from LineAt6Am into PQ.%n", args));
        logWriter.write(String.format("%s", pq.createCustPQ()));
        logWriter.write(String.format("Dump of current heap (array) - %d nodes:%n", pq.getHeap().getNodeCount()));
        logWriter.write(pq.dumpNodes());
        logWriter.write(String.format("Will now process CustomerEvents data.%n"));

        BufferedReader eventReader = new BufferedReader(new FileReader("CustomerEvents.txt"));
        String line = null;
        while ((line = eventReader.readLine()) != null) {
            if (line.startsWith("CustomerServed")) {
                Customer nextCustomer = pq.serveNextCustInPQ();
                logWriter.write(String.format("SERVED:  %s%n", nextCustomer));
            } else if (line.startsWith("CustomerArrives")) {
                String customerLine = line.substring(line.indexOf("  ") + 2);
                Customer newCustomer = pq.addCustToPQ(customerLine);
                logWriter.write(String.format("ADDED:  %s%n", newCustomer));
            }
        }
        eventReader.close();

        logWriter.write(String.format("Finished processing CustomerEvents data.%n"));
        logWriter.write(String.format("Dump of current heap (array) - %d nodes:%n", pq.getHeap().getNodeCount()));
        logWriter.write(pq.dumpNodes());
        logWriter.write(String.format("STORE CLOSES%n"));
        logWriter.write(String.format("Will now automatically serve %d remaining customers%n", pq.getHeap().getNodeCount()));

        logWriter.write(pq.serveRestOfCustInPQ());
        logWriter.write(String.format("Heap is now empty - 0 nodes%n>> Program ending%n"));
        logWriter.close();
    }
}
