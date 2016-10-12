package harmony.citygraphsimulation

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import harmony.citygraphsimulation.screens.MapView
import harmony.citygraphsimulation.util.Assets
import harmony.citygraphsimulation.util.Settings

class CityGraph : Game() {
  lateinit var batch: SpriteBatch
  private var fullScreen = false
  
  override fun create() {
    batch = SpriteBatch()
    Assets
    setScreen(MapView(this))
  }
  
  override fun render() {
    Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  
    if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
      if (fullScreen) {
        Gdx.graphics.setWindowedMode(Settings.resolution.x.toInt(), Settings.resolution.y.toInt())
        fullScreen = false
      } else {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.displayMode)
        fullScreen = true
      }
    }
  
    super.render()
  }
  
  override fun dispose() {
    super.dispose()
    screen.dispose()
    batch.dispose()
    Assets.dispose()
  }
}
