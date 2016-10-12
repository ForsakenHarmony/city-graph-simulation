package harmony.citygraphsimulation.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Logger
import harmony.citygraphsimulation.components.NodeComponent
import harmony.citygraphsimulation.components.TransformComponent
import harmony.citygraphsimulation.util.Mappers
import java.util.*

/**
 * @author ForsakenHarmony
 */
class RenderingSystem : IteratingSystem {
  
  private val log: Logger = Logger(javaClass.simpleName.toString(), Logger.DEBUG)
  
  private var batch: SpriteBatch
  private val shapeRenderer: ShapeRenderer
  
  private var renderQueue: Array<Entity>
  private var comparator: Comparator<Entity>
  private var cam: OrthographicCamera
  
  init {
    shapeRenderer = ShapeRenderer()
    shapeRenderer.setAutoShapeType(true)
    
    renderQueue = Array()
    
    comparator = Comparator { a, b -> Math.signum(Mappers.TRANSFORM.get(b).pos.z - Mappers.TRANSFORM.get(a).pos.z).toInt() }
  }
  
  override fun update(deltaTime: Float) {
    super.update(deltaTime)
    
    renderQueue.sort(comparator)
    
    cam.update()
    batch.projectionMatrix = cam.combined
    shapeRenderer.projectionMatrix = cam.combined
    
    draw()
    
    renderQueue.clear()
  }
  
  fun draw() {
    batch.begin()
    shapeRenderer.begin()
    shapeRenderer.color = Color.GREEN
    
    for (entity: Entity in renderQueue) {
      val trans = Mappers.TRANSFORM.get(entity)
      val node = Mappers.NODE.get(entity)
      
      shapeRenderer.circle(trans.x, trans.y, 0.00004f, 3)
    }
    
    batch.end()
    shapeRenderer.end()
  }
  
  constructor(camera: OrthographicCamera, batch: SpriteBatch) : super(Family.all(TransformComponent::class.java, NodeComponent::class.java).get()) {
    this.cam = camera
    this.batch = batch
  }
  
  override fun processEntity(entity: Entity?, deltaTime: Float) {
    renderQueue.add(entity)
  }
}