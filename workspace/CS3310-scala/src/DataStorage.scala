import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc


/* 
 * Note: in scala you can't overload classes only functions
 * I had to use some improvised solutions here.
 */


class DataStorage(val filedata: Iterator[String]) {

  if (filedata.length > 1){
  val fileLines = filedata.toList
  var count = 0
  val codearray = new ListBuffer[String]()
  val valuearray = new ListBuffer[Int]()
  println(fileLines.length)
  try { 
  while (count < (fileLines.length)) {
    val words = fileLines(count).split(",")
    codearray += words(1)
    valuearray += words(1).toList.head.toInt - 64
    count = count + 1;
  }
  
  } catch {
     case e: Exception => "java.lang.ArrayIndexOutOfBoundsException"
  }finally {
     
   } 
  
 
  val codeList = codearray.toList
  val valueList = valuearray.toList
  val sortedList = codeList.sortWith(_ < _)
  scala.tools.nsc.io.File("Backup.csv").writeAll(sortedList.mkString(","))
  }else{
      val databackup = Source.fromFile("Backup.csv", "ISO-8859-1").getLines.toList
    
  }
  }







  