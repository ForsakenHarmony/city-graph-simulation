package harmony.citygraphsimulation.util

import com.badlogic.ashley.core.ComponentMapper
import harmony.citygraphsimulation.components.NodeComponent
import harmony.citygraphsimulation.components.TransformComponent

/**
 * @author ForsakenHarmony
 */
object Mappers {
  val TRANSFORM = ComponentMapper.getFor(TransformComponent::class.java)!!
  val NODE = ComponentMapper.getFor(NodeComponent::class.java)!!
}