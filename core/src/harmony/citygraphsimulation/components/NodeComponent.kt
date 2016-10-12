package harmony.citygraphsimulation.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import java.util.*

/**
 * @author ForsakenHarmony
 */
class NodeComponent: Component {
  var id: Long = 0
  var connections: ArrayList<Entity> = ArrayList()
}