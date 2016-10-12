package harmony.citygraphsimulation.osm

import java.util.*

/**
 * @author ForsakenHarmony
 */
class Relation : Element {
  val id: Long
  var visible: Boolean
  
  val members: ArrayList<Member>
  val tags: MutableMap<String, String>
  
  constructor(id: Long, visible: Boolean) {
    this.id = id
    this.visible = visible
    
    this.members = ArrayList(50)
    this.tags = mutableMapOf()
  }
  
  override fun toString(): String {
    return "RELATION: {id: $id, visible: $visible, members: $members, tags: $tags}"
  }
}