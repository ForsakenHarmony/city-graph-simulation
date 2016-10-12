package harmony.citygraphsimulation.screens

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Logger
import harmony.citygraphsimulation.CityGraph
import harmony.citygraphsimulation.game.World
import harmony.citygraphsimulation.systems.RenderingSystem
import harmony.citygraphsimulation.util.Assets

/**
 * @author ForsakenHarmony
 */
class MapView : ScreenAdapter {
  
  private val game: CityGraph
  private val engine: PooledEngine
  
  private val log: Logger = Logger(javaClass.simpleName.toString(), Logger.DEBUG)
  
  private val guiCam: OrthographicCamera = OrthographicCamera(1280f, 720f)
  private var touchPoint: Vector2 = Vector2()
  
  private val cam: OrthographicCamera
  
  init {
    cam = World.camera
    engine = World.engine
    
    World.createEntitiesForMap(Assets.map!!)
  }
  
  constructor(game: CityGraph) {
    this.game = game
    engine.addSystem(RenderingSystem(cam, game.batch))
  }
  
  fun update(delta: Float) {
    engine.update(delta)
    
    val batch = game.batch
    val font = Assets.font
    
    batch.projectionMatrix = guiCam.projection
    batch.begin()
    font.data.setScale(0.7f)
    font.draw(game.batch, "${cam.position.x} / ${cam.position.y}", -630f, 360f)
    batch.end()
    
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      cam.position.x += 0.05f * delta
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      cam.position.x -= 0.05f * delta
    }
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      cam.position.y += 0.05f * delta
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      cam.position.y -= 0.05f * delta
    }
    
    if (Gdx.input.justTouched()) {
      log.info("click ${unproject(Gdx.input.x, Gdx.input.y)}")
    }
    
    Gdx.input.inputProcessor
  }
  
  override fun render(delta: Float) {
    update(delta)
  }
  
  override fun show() {
    log.info("SHOW")
    Gdx.input.inputProcessor = Processor(this)
  }
  
  override fun hide() {
    log.info("HIDE")
    Gdx.input.inputProcessor = null
    engine.removeAllEntities()
    engine.clearPools()
  }
  
  fun scrolled(amount: Int): Boolean {
    log.info("scrolled: $amount")
    
    cam.zoom *= if (amount == -1) {
      0.5f
    } else {
      2f
    }
    
    if (amount == -1) {
      cam.position.set(unproject(Gdx.input.x, Gdx.input.y).add(campos()).scl(0.5f, 0.5f), 0f)
    } else {
      cam.position.set(campos().sub(unproject(Gdx.input.x, Gdx.input.y).sub(campos())),0f)
    }
    
    return true
  }
  
  val startpos: Vector2 = Vector2()
  val offset: Vector2 = Vector2()
  
  fun dragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
    log.info("dragged: [$screenX, $screenY] $pointer")
    
    val move = unproject(screenX, screenY).sub(offset).sub(campos())
    
    log.info("$move")
    
    startpos.set(unproject(screenX, screenY))
    offset.set(startpos.cpy().sub(campos()))
    
    cam.position.add(-move.x, -move.y, 0f)
    return true
  }
  
  fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    log.info("touchdown [$screenX, $screenY] $pointer $button ")
    
    startpos.set(unproject(screenX, screenY))
    offset.set(startpos.cpy().sub(campos()))
    
    log.info("$startpos, $offset, ${campos()}, ${campos().add(offset)}")
    
    return true
  }
  
  fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    log.info("touchup [$screenX, $screenY] $pointer $button ")
    return true
  }
  
  fun unproject(x: Int, y: Int): Vector2 {
    var pos = Vector3(x.toFloat(), y.toFloat(), 0f)
    pos = cam.unproject(pos)
    return Vector2(pos.x, pos.y)
  }
  
  fun campos(): Vector2 {
    return Vector2(cam.position.x, cam.position.y)
  }
}

class Processor : InputProcessor {
  
  val mapView: MapView
  
  constructor(mapView: MapView) {
    this.mapView = mapView
  }
  
  override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    return mapView.touchUp(screenX, screenY, pointer, button)
  }
  
  override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
    return false
  }
  
  override fun keyTyped(character: Char): Boolean {
    return false
  }
  
  override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
    return mapView.touchDown(screenX, screenY, pointer, button)
  }
  
  override fun scrolled(amount: Int): Boolean {
    return mapView.scrolled(amount)
  }
  
  override fun keyUp(keycode: Int): Boolean {
    return false
  }
  
  override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
    return mapView.dragged(screenX, screenY, pointer)
  }
  
  override fun keyDown(keycode: Int): Boolean {
    return false
  }
}