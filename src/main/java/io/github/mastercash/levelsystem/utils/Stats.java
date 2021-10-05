package io.github.mastercash.levelsystem.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Stats enum
 * Represents what stats are supported to be leveled up
 */
public enum Stats {
  BREWER("brewer"),
  BUILDER("builder"),
  FARMER("farmer"),
  FISHER("fisher"),
  MINER("miner"),
  SLAYER("slayer"),
  TAMER("tamer"),
  LOGGER("logger");

  private final String value;
  private Stats(String value) {
    this.value = value;
  }

  /**
   * Gets the ID associated with this enum value
   * @return
   */
  public String getID() {
    return this.value;
  }

  /**
   * Gets the list of IDs associated with this enum
   * @return list of ids
   */
  public static List<String> GetIDs() {
    List<String> list = new ArrayList<>();
    for(var stat : Stats.values()) {
      list.add(stat.getID());
    }
    return list;
  }
}