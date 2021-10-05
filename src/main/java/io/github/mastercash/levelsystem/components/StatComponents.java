package io.github.mastercash.levelsystem.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.mastercash.levelsystem.components.base.StatComponent;
import io.github.mastercash.levelsystem.components.base.StatComponentImpl;
import io.github.mastercash.levelsystem.utils.Stats;
import java.util.HashMap;
import java.util.Map;

import static io.github.mastercash.levelsystem.components.StatConstants.GetStatID;
import static dev.onyxstudios.cca.api.v3.component.ComponentRegistry.getOrCreate;

/**
 * TODO: document
 */
public final class StatComponents implements EntityComponentInitializer {
  /**
   * 
   */
  public static final Map<Stats, ComponentKey<StatComponent>> StatComponentKeys = getKeys();
  /**
   * 
   * @return
   */
  private static Map<Stats, ComponentKey<StatComponent>> getKeys() {
    var map = new HashMap<Stats, ComponentKey<StatComponent>>();
    for(var stat : Stats.values()) {
      map.put(stat, getOrCreate(GetStatID(stat), StatComponent.class));
    }
    return map;
  }

  @Override
  public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
    for(var entry : StatComponentKeys.entrySet()) {
      registry.registerForPlayers(entry.getValue(), player -> new StatComponentImpl(entry.getKey()), RespawnCopyStrategy.CHARACTER);
    }
  }
}
