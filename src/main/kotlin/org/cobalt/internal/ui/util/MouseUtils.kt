package org.cobalt.internal.ui.util

import org.cobalt.Cobalt.mc

internal inline val mouseX: Double
  get() = mc.mouse.x

internal inline val mouseY: Double
  get() = mc.mouse.y

internal fun isHoveringOver(x: Float, y: Float, width: Float, height: Float): Boolean =
    mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height
