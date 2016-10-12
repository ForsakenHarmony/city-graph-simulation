package harmony.citygraphsimulation.osm

/**
 * @author ForsakenHarmony
 */
class Node : Element {
  val id: Long
  val lat: Float
  val lon: Float
  var visible: Boolean
  
  val tags: MutableMap<String, String>
  
  constructor(id: Long, lat: Float, lon: Float, visible: Boolean) {
    this.id = id
    this.lat = lat
    this.lon = lon
    this.visible = visible
    
    this.tags = mutableMapOf()
  }
  
  override fun toString(): String {
    return "NODE: {id: $id, lat: $lat, lon: $lon, visible: $visible, tags: $tags}"
  }
}