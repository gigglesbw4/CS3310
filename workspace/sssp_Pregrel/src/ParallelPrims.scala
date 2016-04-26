import org.apache.spark._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx.util._
import org.apache.spark.graphx._
import org.apache.spark.graphx.util.GraphGenerators


object ParallelPrims {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Parallel Prims").setMaster("local")
    val sc = new SparkContext(conf)

    //    val sc = new SparkContext("local", "Allen Pregel Test", System.getenv("SPARK_HOME"), SparkContext.jarOfClass(this.getClass))
    // A graph with edge attributes containing distances
val graph = GraphGenerators.logNormalGraph(sc, numVertices = 5, numEParts = sc.defaultParallelism, mu = 4.0, sigma = 1.3).mapEdges(e => e.attr.toDouble)
graph.edges.foreach(println)
graph.vertices.foreach(println)


    val sourceId: VertexId = 0 // The ultimate source

    // Initialize the graph such that all vertices except the root have distance infinity.
    val initialGraph: Graph[(Double, List[VertexId]), Double] = graph.mapVertices((id, _) => if (id == sourceId) (0.0, List[VertexId](sourceId)) else (Double.PositiveInfinity, List[VertexId]()))

    val sssp = initialGraph.pregel((Double.PositiveInfinity, List[VertexId]()), Int.MaxValue, EdgeDirection.Out)(

      // Vertex Program
      (id, dist, newDist) => if (dist._1 < newDist._1) dist else newDist,

      // Send Message
      triplet => {
        if (triplet.srcAttr._1 < triplet.dstAttr._1 - triplet.attr) {
          Iterator((triplet.dstId, (triplet.srcAttr._1 + triplet.attr, triplet.srcAttr._2 :+ triplet.dstId)))
        } else {
          Iterator.empty
        }
      },
      //Merge Message
      (a, b) => if (a._1 < b._1) a else b)
    println(sssp.vertices.collect.mkString("\n"))
  }
}
    
    
    
    
    
    
    
    
//    val logFile = "NodeData.txt"
//
//    val logData = sc.textFile(logFile, 2).cache()
//    // Splitting off header node
//    val headerAndRows = logData.map(line => line.split(",").map(_.trim))
//    val header = headerAndRows.first
//    val data = headerAndRows.filter(_(0) != header(0))
//    // Parse number of Nodes and Edges from header
//    val numNodes = header(0).toInt
//    val numEdges = header(1).toInt
//
//    val vertexArray = new Array[(Long, String)](numNodes)
//
//    val edgeArray = new Array[Edge[Int]](numEdges)
//    // Create vertex array
//    var count = 0
//    for (count <- 0 to numNodes - 1) {
//      vertexArray(count) = (count.toLong + 1, ("v" + (count + 1)).toString())
//    }
//    count = 0
//    val rrdarr = data.take(data.count.toInt)
//    // Create edge array
//    for (count <- 0 to (numEdges - 1)) {
//      val line = rrdarr(count)
//      val cols = line.toList
//      val edge = Edge(cols(0).toLong, cols(1).toLong, cols(2).toInt)
//      edgeArray(count) = Edge(cols(0).toLong, cols(1).toLong, cols(2).toInt)
//    }
//    // Creating graphx graph
//    val vertexRDD: RDD[(Long, (String))] = sc.parallelize(vertexArray)
//    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(edgeArray)
//
//    val graph: Graph[String, Int] = Graph(vertexRDD, edgeRDD)
//
//    graph.triplets.take(6).foreach(println)
//
//  }
//
//}