package io.github.mastercash.levelsystem.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.oroarmor.config.ArrayConfigItem;

import io.github.mastercash.levelsystem.configuration.StatsConfig.StatConfigGroup;
import io.github.mastercash.levelsystem.utils.Stats;
import static io.github.mastercash.levelsystem.Constants.CONFIG;
import net.minecraft.util.Identifier;

/**
 * Constants regarding user configuration file handling
 */
public final class ConfigConstants {
  /**
   * build default Array for a given stat type
   * @param stat stat type for this array
   * @return new {@ArrayConfigItem} storing {@StatConfigGroup}
   */
  public static ArrayConfigItem<StatConfigGroup> getBreakBlockArrayDefault(Stats stat) {
    var arr = new ArrayList<StatConfigGroup>();
    arr.add(getStatGroup());
    //TODO: custom defaulting
    return new ArrayConfigItem<StatConfigGroup>(stat.getID(), arr.toArray(new StatConfigGroup[]{}), stat.getID());
  }

  /**
   * Create empty {@StatConfigGroup} - values: "" and 0.0
   * @return empty {@StatConfigGroup}
   */
  public static StatConfigGroup getStatGroup() {
    return new StatConfigGroup("");
  }

  /**
   * Create {@StatConfigGroup} with given ID
   * @param id Id of the item e.g. "minecraft:dirt"
   * @return {@StatConfigGroup} with given id
   */
  public static StatConfigGroup getStatGroup(String id) {
    return new StatConfigGroup("", id, 1.0);
  }

  /**
   * Create {@StatConfigGroup}
   * @param id Id of the item e.g. "minecraft:dirt"
   * @param exp Exp to give for this item
   * @return {@StatConfigGroup} with given parameters
   */
  public static StatConfigGroup getStatGroup(String id, Double exp) {
    return new StatConfigGroup("",id,exp);
  }
  public static String getNamespace(String root, String... sub) {
    var namespace = root; 
    for(var str : sub) {
      namespace += "." + str;
    }
    return namespace;
  }

  /**
   * Get a {@Map} of the items and the {@Set} of {@StatItem}s associated with it
   * for a given configuration namespace (e.g. "break-blocks")
   * @param namespace namespace of the configuration path (e.g. "break-blocks")
   * @return new {@Map} with given items for a given namespace
   */
  public static Map<Identifier, Set<StatItem>> getItemList(String namespace) {
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

