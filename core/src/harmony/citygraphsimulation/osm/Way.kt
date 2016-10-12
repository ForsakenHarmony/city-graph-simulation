package harmony.citygraphsimulation.osm

import java.util.*

/**
 * @author ForsakenHarmony
 */
class Way:Element {
  val id: Long
  var visible: Boolean
  
  val nodes: ArrayList<Long>
  val tags: MutableMap<String, String>
  
  constructor(id: Long, visible: Boolean) {
    this.id = id
    this.visible = visible
    
    this.nodes = ArrayList(50)
    this.tags = mutableMapOf()
  }
  
  override fun toString(): String {
    return "WAY: {id: $id, visible: $visible, nodes: $nodes, tags: $tags}"
  }
}