import scala.io.Source
import scala.collection.mutable.ListBuffer


object UserApp {
  def printList(args: List[_]): Unit = {
    args.foreach(println)
  }  

  
    def main(args: Array[String]) {
      
     val tranDat = new UIinput("TransData.txt")
     val rawDat = new DataStorage(Iterator("NULL"))    
     
     
     

      
      
    }
  
  
}