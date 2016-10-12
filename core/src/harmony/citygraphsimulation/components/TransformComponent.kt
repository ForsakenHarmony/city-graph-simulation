package harmony.citygraphsimulation.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector3

/**
 * @author ForsakenHarmony
 */
class TransformComponent: Component {
  val pos: Vector3 = Vector3()
  
  var x: Float
    get() = pos.x
    set(value) {
      pos.x = value
    }
  
  var y: Float
    get() = pos.y
    set(value) {
      pos.y = value
    }
  
  var z: Float
    get() = pos.z
    set(value) {
      pos.z = value
    }
  
  
  fun set(x: Float, y: Float) {
    pos.set(x, y, pos.z)
  }
}