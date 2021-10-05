package io.github.mastercash.levelsystem.configuration;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.mastercash.cashconfig.Config;
import io.github.mastercash.cashconfig.Items.BaseConfigItem;
import io.github.mastercash.cashconfig.Items.ConfigGroup;
import io.github.mastercash.cashconfig.Items.ConfigList;
import io.github.mastercash.cashconfig.Items.ConfigNumber;
import io.github.mastercash.cashconfig.Items.BaseConfigItem.Type;
import io.github.mastercash.levelsystem.Constants;
import static io.github.mastercash.levelsystem.Constants.LOGGER;
import io.github.mastercash.levelsystem.utils.Stats;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import static com.google.common.collect.ImmutableList.of;

public class Configuration {
  public static Config CONFIG = new Config(getDefaultConfig(), new File(FabricLoader.getInstance().getConfigDir().toFile(), Constants.MOD_ID + ".json"));
  public static Map<Identifier, Set<StatItem>> getUseItem() { return getGroup("useItem"); }
  public static Map<Identifier, Set<StatItem>> getBreakBlock() { return getGroup("breakBlock"); }
  public static Map<Identifier, Set<StatItem>> getKillEntity() { return getGroup("killEntity"); }
  public static Map<Identifier, Set<StatItem>> getPlaceBlock() { return getGroup("placeBlock"); }

  private static Map<Identifier, Set<StatItem>> getGroup(String groupID) {
    var map = new HashMap<Identifier, Set<StatItem>>();
    // get the group
    var group = (ConfigGroup) CONFIG.getItem(groupID, Type.GROUP);

    // iterate through all the items of the group
    for(var list : group.getValue()) {
      // if the item isn't an array this extraneous unexpected data
      if(!list.IsList()) {
        LOGGER.warn("unexpected config item type for " + Dot(groupID, list.getKey()) + " type found was " + list.getType().toString());
        continue;
      }
      // verify the subtype is what we expect ignore it if it isn't
      if(!list.IsList(Type.GROUP)) {
        LOGGER.warn("unexpected subtype for " + Dot(groupID, list.getKey()) + " type found was " + list.AsList().getSubType().toString());
        continue;
      }
      // iterate through the list of items in the list
      for(var item : list.AsList().getValue()) {
        // check to make sure each item is the type we expect this should be a redundant check but I like to air on the safe side incase something slipped in.
        if(!item.IsGroup()) {
          LOGGER.warn("unexpected config item in " + Dot(groupID, list.getKey()) + " type found was " + item.getType().toString());
          continue;
        }
        // we know its a group, cast it as such;
        var statGroup = item.AsGroup();
        // if the id property doesn't exist this is an invalid item ignore it
        if(!statGroup.HasItem("id")) { 
          LOGGER.warn("key \"id\" missing from " + Dot(groupID, list.getKey()) + " item");
          continue;
        }
        // get the id
        var id = new Identifier(statGroup.GetItem("id").AsString().getValue());
        // get if id exits already
        var containsSet = map.containsKey(id);
        // get or create set
        Set<StatItem> set = containsSet ? map.get(id) : new HashSet<>();
        // get stat enum value
        Stats stat = Stats.valueOf(statGroup.getKey());
        // get exp value
        Float exp = statGroup.GetItem("exp").AsNumber().getValue().floatValue();
        // create the stat item
        var statItem = new StatItem(stat, exp);
        // add it to the set
        set.add(statItem);
        // if the set was created, add it to the map.
        if(!containsSet) {
          map.put(id, set);
        }
      }
    }
    return map;
  }

  private static List<BaseConfigItem<?>> getDefaultConfig() {
    ConfigNumber version = new ConfigNumber("version",Constants.CONFIG_VERSION);
    ConfigGroup useItem = new ConfigGroup("useItem", null);
    ConfigGroup breakBlock = new ConfigGroup("breakBlock", null);
    ConfigGroup killEntity = new ConfigGroup("killEntity", null);
    for(var stat : Stats.values()) {
      useItem.AddItem(new ConfigList(stat.getID(), null, Type.GROUP));
      breakBlock.AddItem(new ConfigList(stat.getID(), null, Type.GROUP));
      killEntity.AddItem(new ConfigList(stat.getID(), null, Type.GROUP));
    }

    return of(version, useItem, breakBlock, killEntity);
  }
  private static String Dot(String first, String... items) {
    String path = first;
    for(var item : items) {
      path += "." + item;
    }
    return path;
  }
}
