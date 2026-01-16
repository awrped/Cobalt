package org.cobalt.api.rotation

import net.minecraft.client.network.ClientPlayerEntity
import org.cobalt.api.util.helper.Rotation

interface IRotationStrategy {

  /**
   * Called every frame to handle rotation logic for this strategy.
   *
   * @param player The client player entity
   * @param targetYaw The target yaw rotation
   * @param targetPitch The target pitch rotation
   * @return Rotation with new yaw & new pitch or null if rotation is complete
   */
  fun onRotate(
    player: ClientPlayerEntity,
    targetYaw: Float,
    targetPitch: Float,
  ): Rotation?

  /**
   * Called when the rotation strategy is started.
   */
  fun onStart() {}

  /**
   * Called when the rotation strategy is stopped.
   */
  fun onStop() {}

}
