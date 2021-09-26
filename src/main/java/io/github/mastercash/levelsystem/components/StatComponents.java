package io.github.mastercash.levelsystem.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.github.mastercash.levelsystem.components.base.StatComponent;
import io.github.mastercash.levelsystem.components.base.StatComponentImpl;
import io.github.mastercash.levelsystem.utils.Stats;
import static io.github.mastercash.levelsystem.components.StatConstants.GetStatID;
import static dev.onyxstudios.cca.api.v3.component.ComponentRegistry.getOrCreate;


public final class StatComponents implements EntityComponentInitializer {
  public static final ComponentKey<StatComponent> BREWER = getOrCreate(GetStatID(Stats.BREWER), StatComponent.class);
  public static final ComponentKey<StatComponent> MINER = getOrCreate(GetStatID(Stats.MINER), StatComponent.class);
  public static final ComponentKey<StatComponent> LOGGER = getOrCreate(GetStatID(Stats.LOGGER), StatComponent.class);
  public static final ComponentKey<StatComponent> BUILDER = getOrCreate(GetStatID(Stats.BUILDER), StatComponent.class);
  public static final ComponentKey<StatComponent> FARMER = getOrCreate(GetStatID(Stats.FARMER), StatComponent.class);
  public static final ComponentKey<StatComponent> FISHER = getOrCreate(GetStatID(Stats.FISHER), StatComponent.class);
  public static final ComponentKey<StatComponent> SLAYER = getOrCreate(GetStatID(Stats.SLAYER), StatComponent.class);
  public static final ComponentKey<StatComponent> TAMER = getOrCreate(GetStatID(Stats.TAMER), StatComponent.class);
  @Override
  public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
    registry.registerForPlayers(BREWER, player -> new StatComponentImpl(Stats.BREWER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(MINER, player -> new StatComponentImpl(Stats.MINER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(LOGGER, player -> new StatComponentImpl(Stats.LOGGER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(BUILDER, player -> new StatComponentImpl(Stats.BUILDER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(FARMER, player -> new StatComponentImpl(Stats.FARMER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(FISHER, player -> new StatComponentImpl(Stats.FISHER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(SLAYER, player -> new StatComponentImpl(Stats.SLAYER), RespawnCopyStrategy.CHARACTER);
    registry.registerForPlayers(TAMER, player -> new StatComponentImpl(Stats.TAMER), RespawnCopyStrategy.CHARACTER);
  }
}
