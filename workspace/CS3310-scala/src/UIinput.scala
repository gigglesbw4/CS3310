import scala.io.Source
import scala.collection.mutable.ListBuffer

class UIinput(val filename: String) {



  val transData = Source.fromFile(filename, "ISO-8859-1").getLines.toList
  val test = filterList(transData)
  
  
  
  
  
  

    def filterList(transData: List[_]): Unit = {
    var count = 0
      val argarry = new ListBuffer[String]()
    while (count < (transData.length)) {
      transData(count).toString().take(1) match {
        case "A" => Aswitch(transData(count).toString())
        case "I" => Iswitch(transData(count).toString())
        case "S" => Sswitch(transData(count).toString())
        case "D" => Dswitch(transData(count).toString())
        case whoa => "Unexpected case: " + whoa.toString
      }
     count = count + 1
    }

  
  }
  
  
  
  
  
   def Aswitch (strTok: String): Unit = {
   println("SHOW ALL") 
   }
   
   def Iswitch (strTok: String): Unit = {
   println("INSERT") 
   }
   
   def Sswitch (strTok: String): Unit = {
   println("Search") 
   }
   
   def Dswitch (strTok: String): Unit = {
   println("Delete") 
   }
   
   

}