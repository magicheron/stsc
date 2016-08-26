package stsc

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

import org.scalatest.{FlatSpec, Matchers}

import java.io.File

class ParallelClusterTest extends FlatSpec with Matchers {
    "The simplest test" should "work" in {
        val conf = new SparkConf().setAppName("ParallelClusterTest").setMaster("spark://main:7077")
        val sc = new SparkContext(conf)
        sc.addJar("/home/Armand/stsc/target/scala-2.11/stsc-assembly-1.0.jar")

        val dataPath = getClass.getResource("/datasetforkdt1.csv").getPath()
        val ttPath = getClass.getResource("/kdt1.csv").getPath()
        STSC.sparkCluster(sc, dataPath, ttPath, "clusters")
        STSC.sparkCluster(sc, dataPath, ttPath, "clusterCenters.csv", true)
        sc.stop()
    }
}
