package harmony.citygraphsimulation.osm

import java.util.*

/**
 * @author ForsakenHarmony
 */
class Map {
  var minlat: Float = 0.0f
  var maxlat: Float = 0.0f
  var minlon: Float = 0.0f
  var maxlon: Float = 0.0f
  
  val nodes = ArrayList<Node>(50)
  val ways = ArrayList<Way>(50)
  val relations = ArrayList<Relation>(50)
  
  override fun toString(): String {
    return "MAP: {minlat: $minlat, maxlat: $maxlat, minlon: $minlon, maxlon: $maxlon, nodes: ${nodes.size}, ways: ${ways.size}, relations: ${relations.size}}"
  }
}