package io.github.mastercash.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.mastercash.levelsystem.events.AttackedEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

@Mixin(ServerPlayerEntity.class)
public class MixinEntityPlayer {
	@Inject(method = "attack", at = @At("RETURN"))
	public void onPlayerInteractEntity(Entity target, CallbackInfo info) {
    ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
    AttackedEntityCallback.EVENT.invoker().interact(player, player.getEntityWorld(), Hand.MAIN_HAND, target);

  }
}
