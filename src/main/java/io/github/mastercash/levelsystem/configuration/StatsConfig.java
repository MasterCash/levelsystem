package io.github.mastercash.levelsystem.configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.oroarmor.config.ArrayConfigItem;
import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;

import io.github.mastercash.levelsystem.utils.Stats;
import net.fabricmc.loader.api.FabricLoader;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.ImmutableList.of;
import static io.github.mastercash.levelsystem.Constants.MOD_ID;

public class StatsConfig extends Config {

  public StatsConfig() {
    super(of(BreakBlocks), new File(FabricLoader.getInstance().getConfigDir().toFile(), MOD_ID + ".json"), MOD_ID);
  }
  public static StatsConfigGroup BreakBlocks = new StatsConfigGroup("break-blocks");
  public static StatsConfigGroup PlaceBlocks = new StatsConfigGroup("place-blocks");
  public static StatsConfigGroup UseItem = new StatsConfigGroup("use-item");

  public static class StatsConfigGroup extends ConfigItemGroup {
    public StatsConfigGroup(String name) {
      super(copyOf(stats.values()), name);
    }

    public static Map<Stats, ArrayConfigItem<StatConfigGroup>> stats = getStatsArrays();
    public static Map<Stats, ArrayConfigItem<StatConfigGroup>> getStatsArrays() {
      var map = new HashMap<Stats, ArrayConfigItem<StatConfigGroup>>();
      for(var stat : Stats.values()) {
        map.put(stat, ConfigConstants.getBreakBlockArrayDefault(stat));
      }
      return map;
    }
  }
  
  public static class StatConfigGroup extends ConfigItemGroup {
    
    public StatConfigGroup(String name) {
      super(of(ID, EXP), name);
    }
    public StatConfigGroup(String name, String id) {
      super(of(
        new ConfigItem<String>("id", id, "id"),
        EXP
      ), name);
    }
    public StatConfigGroup(String name, String id, Double exp) {
      super(of(
        new ConfigItem<String>("id", id, "id"),
        new ConfigItem<Double>("exp", exp, "exp")
      ), name);
    }

    public static ConfigItem<String> ID = new ConfigItem<String>("id", "", "id");
    public static ConfigItem<Double> EXP = new ConfigItem<Double>("exp", 0.0, "exp");
  }
}