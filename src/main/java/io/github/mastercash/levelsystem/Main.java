package io.github.mastercash.levelsystem;

import io.github.mastercash.levelsystem.commands.StatsCommandBuilder;
import io.github.mastercash.levelsystem.utils.ConstantGenerator;
import io.github.mastercash.levelsystem.utils.Stat;
import static io.github.mastercash.levelsystem.Constants.CONFIG;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer, DedicatedServerModInitializer {

  public static void main(String[] args) {
    Map<String, Set<Integer>> map = new HashMap<>();
    var set = new HashSet<Integer>();
    map.put("test", set);
    set.add(1);
    var set2 = map.get("test");
    var bool = set2.contains(1);
    System.out.println(bool);
    set.add(3);
    set2 = map.get("test");
    System.out.println(set2.contains(3));
  }

  @Override
  public void onInitialize() {
    // Load Config file
    CONFIG.readConfigFromFile();
    CONFIG.saveConfigToFile();
    ServerLifecycleEvents.SERVER_STOPPED.register(instance -> CONFIG.saveConfigToFile());

    CommandRegistrationCallback.EVENT.register(StatsCommandBuilder::register);
    PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
      Item item = state.getBlock().asItem();
      String itemName = item.toString();
      ConstantGenerator.generate();
      Identifier id = Constants.BlockToIdMap.get(itemName);
      if(id != null) {
        var value = Constants.BlockToExpMap.get(itemName); 
        if(value != null) {
          value = 1.0f;
        }
        Stat.Increment(id, player, value);
      }
    });
  }

  @Override
  public void onInitializeServer() {
  }

}
