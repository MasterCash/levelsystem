package io.github.mastercash.levelsystem;

import io.github.mastercash.levelsystem.commands.StatsCommandBuilder;
import io.github.mastercash.levelsystem.events.AttackedEntityCallback;
import io.github.mastercash.levelsystem.utils.Stat;

import static io.github.mastercash.levelsystem.configuration.Configuration.CONFIG;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer, DedicatedServerModInitializer {

  @Override
  public void onInitialize() {
    // Load Config file
    if(CONFIG.hasFile()) {
      CONFIG.readFile();
    } else {
      // save config if doesn't exist
      CONFIG.saveFile();
    }
    ServerLifecycleEvents.SERVER_STOPPED.register(instance -> CONFIG.saveFile());

    CommandRegistrationCallback.EVENT.register(StatsCommandBuilder::register);

    PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
      var id = Registry.BLOCK.getId(state.getBlock());
      if(id.equals(Registry.BLOCK.getDefaultId())) return;
      if(!Constants.BreakBlocksList.get().containsKey(id)) return;
      var statItems = Constants.BreakBlocksList.get().get(id);
      for(var statItem : statItems) {
        Stat.Increment(statItem.Stat, player, statItem.EXP);
      }
    });

    AttackedEntityCallback.EVENT.register((player, world, hand, target) -> {
      if(target instanceof LivingEntity entity) {
        if(!entity.isAlive()) {
          var id = Registry.ENTITY_TYPE.getId(entity.getType());
          if(id.equals(Registry.ENTITY_TYPE.getDefaultId())) return ActionResult.PASS;
          if(!Constants.KillEntityList.get().containsKey(id)) return ActionResult.PASS;
          var statItems = Constants.KillEntityList.get().get(id);
          for(var statItem : statItems) {
            Stat.Increment(statItem.Stat, player, statItem.EXP);
          }
        }
      }

      return ActionResult.PASS;
    });

    UseItemCallback.EVENT.register((player, world, hand) -> {
      var returnValue = TypedActionResult.pass(ItemStack.EMPTY);
      if(!player.getActiveItem().equals(player.getStackInHand(hand))) return returnValue;
      if(!player.getActiveItem().isEmpty() && player.isUsingItem()) {
        var id = Registry.ITEM.getId(player.getActiveItem().getItem());
        if(id.equals(Registry.ITEM.getDefaultId())) return returnValue;
        if(!Constants.UseItemList.get().containsKey(id)) return returnValue;
        var statItems = Constants.UseItemList.get().get(id);
        for(var statItem: statItems) {
          Stat.Increment(statItem.Stat, player, statItem.EXP);
        }
      }
      return returnValue;
    });

  }

  @Override
  public void onInitializeServer() {
  }

}
