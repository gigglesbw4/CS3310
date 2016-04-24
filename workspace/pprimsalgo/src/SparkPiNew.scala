import scala.math.random
import org.apache.spark._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

/** Computes an approximation to pi */
object SparkPiNew {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Graphx Test").setMaster("local")
    val sc = new SparkContext(conf)
    val vertexArray = Array(
      (1L, ("Alice", 28)),
      (2L, ("Bob", 27)),
      (3L, ("Charlie", 65)),
      (4L, ("David", 42)),
      (5L, ("Ed", 55)),
      (6L, ("Fran", 50)))
    val edgeArray = Array(
      Edge(2L, 1L, 7),
      Edge(2L, 4L, 2),
      Edge(3L, 2L, 4),
      Edge(3L, 6L, 3),
      Edge(4L, 1L, 1),
      Edge(5L, 2L, 2),
      Edge(5L, 3L, 8),
      Edge(5L, 6L, 3))

    val vertexRDD: RDD[(Long, (String, Int))] = sc.parallelize(vertexArray)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(edgeArray)

    val graph: Graph[(String, Int), Int] = Graph(vertexRDD, edgeRDD)

    graph.vertices.filter {
      case (id, (name, age)) => age > 30
    }.collect.foreach {
      case (id, (name, age)) => println(s"$name is $age")
    }

    //    val logFile = "/usr/local/spark/README.md" // Should be some file on your system
    //    val conf = new SparkConf().setAppName("Graphx Test")
    //    val sc = new SparkContext(conf)
    //    val logData = sc.textFile(logFile, 2).cache()
    //    val numAs = logData.filter(line => line.contains("a")).count()
    //    val numBs = logData.filter(line => line.contains("b")).count()
    //    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}