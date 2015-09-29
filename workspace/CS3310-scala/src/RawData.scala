import scala.io.Source


/* This reads the RawData.csv into memory. Note that Scala's I/O does not provide
 * a way to read line by line into memory. Ill try to come up with a solution for this later
 * http://stackoverflow.com/questions/4458864/whats-the-right-way-to-use-scala-io-source
 */

class RawData(val filename: String) {
  
  val file = Source.fromFile(filename, "ISO-8859-1").getLines

}