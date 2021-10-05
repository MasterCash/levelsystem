package io.github.mastercash.levelsystem.utils;

import java.util.NoSuchElementException;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import io.github.mastercash.levelsystem.components.StatComponents;
import io.github.mastercash.levelsystem.components.base.StatComponent;
import net.minecraft.entity.Entity;

/**
 * TODO: Comment
 */
public class Stat {
  /**
   * 
   * @param stat
   * @param provider
   * @param value
   * @throws NoSuchElementException
   * @throws ClassCastException
   */
  public static void Increment(Stats stat, Entity provider, float value) throws NoSuchElementException, ClassCastException {
    var compKey = get(stat);
    var comp = compKey.get(provider);
    comp.Increment(value);
  }

  /**
   * 
   * @param stat
   * @param provider
   * @param value
   */
  public static void Decrement(Stats stat, Entity provider, float value) throws NoSuchElementException, ClassCastException {
    var compKey = get(stat);
    var comp = compKey.get(provider);
    comp.Decrement(value);
  }

  /**
   * 
   * @param stat
   * @param provider
   * @return
   */
  public static float Get(Stats stat, Entity provider) throws NoSuchElementException, ClassCastException {
    var compKey = get(stat);
    var comp = compKey.get(provider);
    return comp.getValue();
  }

  /**
   * @param stat
   * @param provider
   * @return
   */
  public static boolean Exists(Stats stat, Entity provider) {
    var compKey = get(stat);
    var comp = compKey.getNullable(provider);
    return comp != null;
  }

  /**
   * 
   * @param stat
   * @return
   */
  private static ComponentKey<StatComponent> get(Stats stat) {
    return StatComponents.StatComponentKeys.get(stat);
  }

}
