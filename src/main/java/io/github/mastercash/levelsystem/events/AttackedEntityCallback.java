package io.github.mastercash.levelsystem.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface AttackedEntityCallback {
  Event<AttackedEntityCallback> EVENT = EventFactory.createArrayBacked(AttackedEntityCallback.class, (listeners) -> (player, world, hand, entity) -> {
    for (AttackedEntityCallback event : listeners) {
      ActionResult result = event.interact(player, world, hand, entity);

      if(result != ActionResult.PASS) {
        return result;
      }
    }
    return ActionResult.PASS;
  }
  );
  ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity);
}
