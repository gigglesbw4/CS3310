import scala.collection.mutable.ListBuffer
import scala.io.Source
import _root_.scala.tools.nsc
import scala.annotation.tailrec

/* 
 * Note: in scala you can't overload classes only functions
 * I had to use some improvised solutions here.
 */

class DataStorage(val UIoutput: UIoutput) {

  

  def frombackup: String = {
    val databackup = Source.fromFile("Backup.csv", "ISO-8859-1").getLines.mkString(",")
    return databackup
  }

  
  
    def finshup: Unit = {
    val test = new handler()
    val rawdat = test.addtolist(args)

    val codeList = codearray.toList
    val valueList = valuearray.toList

    scala.tools.nsc.io.File("Backup.csv").writeAll(rawdat.mkString(","))

  }
  
    
// Provides a sorted backup since my BST is broken    
    
  def fromraw(filedata: Iterator[String]): Unit = {
    val fileLines = filedata.toList
    var count = 0
    val codearray = new ListBuffer[String]()
    val valuearray = new ListBuffer[Int]()
    while (count < (fileLines.length)) {
      val words = fileLines(count).split(",")
      codearray += words(1)
      valuearray += words(1).toList.head.toInt - 64
      count = count + 1;
    }

    val codeList = codearray.toList
    val valueList = valuearray.toList
    val sortedList = codeList.sortWith(_ < _)

    scala.tools.nsc.io.File("Backup.csv").writeAll(sortedList.mkString(","))

  }
  
  
  
// My attempts to make a BST, if I had more time I would restart this whole process
// Works on Setup but not on UserApp   
    
    def insert(args: List[_]): Unit = {

    val test = new handler()
    val rawdat = test.addtolist(args)
   

    val item = rawdat(test.i - 1).toList(1).toString().toUpperCase()

    val itemval = (item.toList.head.toDouble - 64)
    val nums = 0 to test.i - 1
    var first = 1;
    var last = test.i - 1;
    var middle = first + (first + last) / 2;
    var count = test.i - 1;
    if (count > 0) {
       println(rawdat(test.i - 1).toList(1)," vs parent",rawdat(test.i - 2).toList(1))
count=0
      while (count < (test.i - 1)) {


        if (rawdat(count).toList(1).toString().toUpperCase().compareTo(item)
          < 0) {
          first = middle + 1;
          println("RIGHT")
        } else {
          last = middle - 1;
          println("LEFT")
        }
count=count+1
      }
    }
  }
    


// Handler handles the stored Lists from insert
// *Note: Scala doesn't really have static variables

object handler {
  private var current = 0
  private var lch = 0
  private var rch = 0
  private val codearray = new ListBuffer[List[_]]()
  private def inc = { current += 1; current }
    private def linc = { lch += 1; lch }
      private def rinc = { rch += 1; rch }
  private def appender(args: List[_]) = { codearray += args; codearray }

}

class handler {
  val i = handler.inc
  val linc =  handler.lch
   val rinc =  handler.lch

  def addtolist(args: List[_]): List[List[_]] = {
    return handler.appender(args).toList

  }

}
  
  

















  
