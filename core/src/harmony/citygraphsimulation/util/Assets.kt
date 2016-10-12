package harmony.citygraphsimulation.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Logger
import harmony.citygraphsimulation.osm.Map
import harmony.citygraphsimulation.osm.Parser
import java.io.File

/**
 * @author ForsakenHarmony
 */
object Assets {
  
  private val log: Logger = Logger(javaClass.simpleName.toString(), Logger.DEBUG)
  
  val map: Map?
  val font: BitmapFont
  
  init {
    font = BitmapFont(Gdx.files.internal("fonts/visitor.fnt"), Gdx.files.internal("fonts/visitor.png"),false)
    
    map = Parser.parse(File("map1.osm"))
    log.info(map!!.ways[10].toString())
  }
  
  fun dispose(){
    0
  }
}