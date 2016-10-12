package harmony.citygraphsimulation.game

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.OrthographicCamera
import harmony.citygraphsimulation.components.NodeComponent
import harmony.citygraphsimulation.components.TransformComponent
import harmony.citygraphsimulation.osm.Map
import harmony.citygraphsimulation.osm.Node
import harmony.citygraphsimulation.util.Constants

/**
 * @author ForsakenHarmony
 */
object World {
  var engine: PooledEngine
  var camera: OrthographicCamera
  
  init {
    camera = OrthographicCamera(Constants.FRUSTUM_WIDTH, Constants.FRUSTUM_HEIGHT)
    camera.position.y = 49.857f
    camera.position.x = 9.0824f
    camera.zoom = 0.005f
    engine = PooledEngine(40, 200, 40, 200)
  }
  
  fun createEntitiesForMap(map: Map){
    for (node: Node in map.nodes) {
      val entity = engine.createEntity()
      val transformC = engine.createComponent(TransformComponent::class.java)
      val nodeC = engine.createComponent(NodeComponent::class.java)
      
      transformC.set(node.lon, node.lat)
      nodeC.id = node.id
      
      entity.add(transformC)
      entity.add(nodeC)
      
      engine.addEntity(entity)
      
//      println("ADDED NODE: ${transformC.pos}")
    }
  }
}