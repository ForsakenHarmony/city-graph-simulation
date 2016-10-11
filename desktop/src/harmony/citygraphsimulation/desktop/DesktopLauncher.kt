package harmony.citygraphsimulation.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import harmony.citygraphsimulation.CityGraph

object DesktopLauncher {
  @JvmStatic fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()
    LwjglApplication(CityGraph(), config)
  }
}
