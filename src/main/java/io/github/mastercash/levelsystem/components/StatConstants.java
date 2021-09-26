package io.github.mastercash.levelsystem.components;

import java.util.HashMap;
import java.util.Map;

import io.github.mastercash.levelsystem.utils.ComponentFormatter;
import io.github.mastercash.levelsystem.utils.Stats;
import net.minecraft.util.Identifier;
import static io.github.mastercash.levelsystem.Constants.MOD_ID;


public class StatConstants {
  private static Map<String, Identifier> ids = new HashMap<>(); 
  
  public static Identifier GetStatID(String key) {
    if(ids.containsKey(key)) return ids.get(key);
    var id = new Identifier(ComponentFormatter.Get(MOD_ID, key));
    ids.put(key, id);
    return id;
  }
  public static Identifier GetStatID(Stats stat) {
    return GetStatID(stat.getID());
  }
}