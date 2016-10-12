package harmony.citygraphsimulation.osm

/**
 * @author ForsakenHarmony
 */
class Member {
  val type: String
  val ref: Long
  val role: String
  
  constructor(type: String, ref: Long, role: String) {
    this.type = type
    this.ref = ref
    this.role = role
  }
  
  override fun toString(): String {
    return "MEMBER: {type: $type, ref: $ref, role: $role}"
  }
}