package io.github.mastercash.levelsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.oroarmor.config.Config;

import io.github.mastercash.levelsystem.configuration.StatItem;
import io.github.mastercash.levelsystem.configuration.StatsConfig;
import static io.github.mastercash.levelsystem.configuration.ConfigConstants.getNamespace;
import io.github.mastercash.levelsystem.configuration.StatsConfig.StatConfigGroup;
import io.github.mastercash.levelsystem.utils.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;

public class Constants {
  public static boolean Initialized = false;
  public static Map<String, Identifier> BlockToIdMap = new HashMap<>(); 
  public static Map<String, Float> BlockToExpMap = new HashMap<>();
  public static String MOD_ID = "levelsystem";
  public static Config CONFIG = new StatsConfig();
  public static Lazy<Map<Identifier, Set<StatItem>>> BreakBlocksList = new Lazy<>(() -> {return getItemList(StatsConfig.BreakBlocks.getName());});
  public static Lazy<Map<Identifier, Set<StatItem>>> PlaceBlocksList = new Lazy<>(() -> {return getItemList(StatsConfig.PlaceBlocks.getName());});
  public static Lazy<Map<Identifier, Set<StatItem>>> UseItemList = new Lazy<>(() -> {return getItemList(StatsConfig.UseItem.getName());});

  private static Map<Identifier, Set<StatItem>> getItemList(String namespace) {
    // start new mapping
    var map = new HashMap<Identifier, Set<StatItem>>();

    // for each stat type
    for(var stat : Stats.values()) {

      // get the blocks for that stat
      var statValues = CONFIG.getValue(getNamespace(namespace, stat.getID()), StatConfigGroup[].class);

      for(var value : statValues) {
        String id = null;
        Double exp = 0.0;

        for(var config : value.getConfigs()) {
          if(config.getName().equals("id")) {
            id = (String) config.getValue();
          }
          else if(config.getName().equals("exp")) {
            exp = (Double) config.getValue();
          }
        }

        if(id != null) {
          Identifier ident = new Identifier(id);
          Set<StatItem> set;

          if(!map.containsKey(ident)) {
            set = new HashSet<>();
            map.put(ident, set);
          }
          else {
            set = map.get(ident);
          }

          set.add(new StatItem(stat, exp));
        }
      }
    }
    return map;
  }
}
