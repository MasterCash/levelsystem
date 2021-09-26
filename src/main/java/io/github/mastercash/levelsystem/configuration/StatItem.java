package io.github.mastercash.levelsystem.configuration;

import io.github.mastercash.levelsystem.utils.Stats;

public class StatItem {
  public Stats Stat;
  public Double EXP;

  public StatItem(Stats stat) {
    this(stat, 0.0);
  }
  public StatItem(Stats stat, Double exp) {
    this.Stat = stat;
    this.EXP = exp;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof StatItem item) {
      return this.Stat.equals(item.Stat);
    }
    return false;
  }
}
