package org.cobalt.api.util.helper

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.InputUtil

class KeyBind {

  private var wasPressed = false
  var keyCode: Int = -1

  fun wasPressed(): Boolean {
    if (keyCode == -1) return false
    val mc = MinecraftClient.getInstance()

    val isPressed = mc.currentScreen == null
        && InputUtil.isKeyPressed(mc.window, keyCode)

    return (isPressed && !wasPressed).also {
      wasPressed = isPressed
    }
  }

}
