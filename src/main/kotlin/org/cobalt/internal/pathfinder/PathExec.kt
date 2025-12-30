package org.cobalt.internal.pathfinder

import net.minecraft.client.network.ClientPlayerEntity
import org.cobalt.api.pathfinder.IPathExec

object PathExec : IPathExec {

  override fun onTick(it: ClientPlayerEntity) {}
  override fun onWorldRenderLast(it: ClientPlayerEntity) {}

}
