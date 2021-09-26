package io.github.mastercash.levelsystem.utils;

import java.util.ArrayList;
import java.util.List;

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
  public String getID() {
    return this.value;
  }

  public static List<String> GetIDs() {
    List<String> list = new ArrayList<>();
    for(var stat : Stats.values()) {
      list.add(stat.getID());
    }
    return list;
  }
}