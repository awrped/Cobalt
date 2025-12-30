package org.cobalt.internal.ui.panel.panels

import java.awt.Color
import org.cobalt.api.addon.Addon
import org.cobalt.api.util.ui.NVGRenderer
import org.cobalt.internal.loader.AddonLoader
import org.cobalt.internal.ui.UIComponent
import org.cobalt.internal.ui.components.UIAddonEntry
import org.cobalt.internal.ui.components.UITopbar
import org.cobalt.internal.ui.panel.UIPanel
import org.cobalt.internal.ui.screen.UIConfig
import org.cobalt.internal.ui.util.isHoveringOver

class UIModuleList(
  private val metadata: AddonLoader.AddonMetadata,
  private val addon: Addon,
) : UIPanel(
  x = 0F,
  y = 0F,
  width = 890F,
  height = 600F
) {

  private val topBar = UITopbar("Modules")
  private val entries = AddonLoader.getAddons().map { UIAddonEntry(it.first, it.second) }

  private val backButton = UIBackButton()

  init {
    components.addAll(entries)
    components.addAll(
      listOf(backButton, topBar)
    )
  }

  override fun render() {
    NVGRenderer.rect(x, y, width, height, Color(18, 18, 18).rgb, 10F)

    topBar
      .updateBounds(x, y)
      .render()

    backButton
      .updateBounds(x + 20F, y + topBar.height + 20F)
      .render()
  }

  private class UIBackButton : UIComponent(
    x = 0F,
    y = 0F,
    width = 100F,
    height = 30F
  ) {

    // TODO: Finish Rendering
    override fun render() {
      NVGRenderer.hollowRect(x, y, width, height, 1F, Color(42, 42, 42).rgb, 5F)
    }

    override fun mouseClicked(button: Int): Boolean {
      if (isHoveringOver(x, y, width, height)) {
        UIConfig.swapBodyPanel(UIAddonList())
      }

      return false
    }

  }

}
