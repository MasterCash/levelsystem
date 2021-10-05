package io.github.mastercash.levelsystem;

import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;

import io.github.mastercash.levelsystem.configuration.Configuration;
import io.github.mastercash.levelsystem.configuration.StatItem;

/**
 * TODO: Comment
 */
public class Constants {
  public static Float CONFIG_VERSION = 1.0f;
  public static boolean Initialized = false;
  public static String MOD_ID = "levelsystem";
  public static Lazy<Map<Identifier, Set<StatItem>>> BreakBlocksList = new Lazy<>(() -> {return Configuration.getBreakBlock();});
  public static Lazy<Map<Identifier, Set<StatItem>>> PlaceBlocksList = new Lazy<>(() -> {return Configuration.getPlaceBlock();});
  public static Lazy<Map<Identifier, Set<StatItem>>> UseItemList = new Lazy<>(() -> {return Configuration.getUseItem();});
  public static Lazy<Map<Identifier, Set<StatItem>>> KillEntityList = new Lazy<>(() -> {return Configuration.getKillEntity();});
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  }
