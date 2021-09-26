package io.github.mastercash.levelsystem.utils;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import io.github.mastercash.levelsystem.components.StatComponents;
import io.github.mastercash.levelsystem.components.StatConstants;
import io.github.mastercash.levelsystem.components.base.StatComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class Stat {
  public static void Increment(Identifier id, Entity provider, float value) {
    var comp = get(id);
    if(comp != null) {
      var stat = comp.get(provider);
      stat.Increment(value);
    }
  }

  public static void Decrement(Identifier id, Entity provider, float value) {
    var comp = get(id);
    if(comp != null) {
      var stat = comp.get(provider);
      stat.Decrement(value);
    }
  }

  public static float Get(Identifier id, Entity provider) {
    var comp = get(id);
    if(comp != null) {
      var stat = comp.get(provider);
      return stat.getValue();
    }
    return 0;
  }

  public static boolean Exists(Identifier id, Entity provider) {
    var comp = get(id);
    if(comp == null) return false;
    var stat = comp.getNullable(provider);
    return stat != null;
  }

  private static ComponentKey<StatComponent> get(Identifier id) {
    /*
    if(id.equals(StatConstants.BREWER)) return StatComponents.BREWER;
    if(id.equals(StatConstants.MINER)) return StatComponents.MINER;
    if(id.equals(StatConstants.LOGGER)) return StatComponents.LOGGER;
    if(id.equals(StatConstants.BUILDER)) return StatComponents.BUILDER;
    if(id.equals(StatConstants.FARMER)) return StatComponents.FARMER;
    if(id.equals(StatConstants.FISHER)) return StatComponents.FISHER;
    if(id.equals(StatConstants.SLAYER)) return StatComponents.SLAYER;
    if(id.equals(StatConstants.TAMER)) return StatComponents.TAMER;
    */
    return null;
  }

}
