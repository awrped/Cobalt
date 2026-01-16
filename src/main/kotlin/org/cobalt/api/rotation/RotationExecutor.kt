package org.cobalt.api.rotation

import net.minecraft.client.MinecraftClient
import org.cobalt.api.event.annotation.SubscribeEvent
import org.cobalt.api.event.impl.render.WorldRenderEvent
import org.cobalt.api.util.AngleUtils
import org.cobalt.api.util.PlayerUtils
import org.cobalt.api.util.helper.Rotation
import org.cobalt.api.util.player.MovementManager

object RotationExecutor {

  private val mc: MinecraftClient =
    MinecraftClient.getInstance()

  private var targetYaw: Float = 0F
  private var targetPitch: Float = 0F

  private var currStrat: IRotationStrategy? = null
  private var isRotating: Boolean = false

  fun rotateTo(
    endRot: Rotation,
    strategy: IRotationStrategy,
  ) {
    stopRotating()

    targetYaw = endRot.yaw
    targetPitch = endRot.pitch
    currStrat = strategy

    strategy.onStart()
    MovementManager.setLookLock(true)
    isRotating = true
  }

  fun stopRotating() {
    currStrat?.onStop()
    currStrat = null
    isRotating = false
    MovementManager.setLookLock(false)
  }

  fun isRotating(): Boolean {
    return isRotating
  }

  @SubscribeEvent
  fun onRotate(
    event: WorldRenderEvent.Last,
  ) {
    val player = mc.player ?: return

    if (!isRotating) {
      return
    }

    currStrat?.let {
      val result = it.onRotate(
        player,
        targetYaw,
        targetPitch
      )

      if (result == null) {
        stopRotating()
      } else {
        player.yaw = result.yaw
        player.pitch = result.pitch
      }
    }
  }

}
