import scala.io.Source
import scala.collection.mutable.ListBuffer

/**
 * @author Blake Wrege
 * CS3310 Assignment 1
 */

object Setup {

  def main(args: Array[String]) {
    val UIoutput = new UIoutput
    UIoutput.start("-->> SETUP started")
    val indata = new RawData("RawData.csv", UIoutput)

    val outdata = new DataStorage(UIoutput)
    var count = 0
    val test = new BinarySearchTree
    // Reading the file line by line

    val lines = new ListBuffer[String]()

    while (count < indata.file.length) {
      val lister = indata.readline(indata.file(count))
 //  test.insert(lister(0).toString())
 //     test.insert(count.toString())
     outdata.insert(indata.readline(indata.file(count)))
//      println(indata.readline(indata.file(count)))
      count = count + 1
    }
   // test.inOrderTraversal

    UIoutput.displayThis("\n-->> OPENED Backup file")

    //outdata.fromraw(indata.file)
     outdata.finishup
    UIoutput.displayThis("\n-->> CLOSED Backup file")
    
    UIoutput.displayThis("\n-->> SETUP finished – inserted 26 countries into DataStorage")

  }

  def printList(args: List[List[_]]): Unit = {
    args(0).foreach(println)
  }

}



  

  
  
  
  
