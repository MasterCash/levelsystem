package io.github.mastercash.levelsystem.commands.suggestions;

import java.util.List;

import io.github.mastercash.levelsystem.utils.Stats;

public class StatSuggestions extends CommandSuggestions {

  @Override
  public List<String> suggestions() {
    return Stats.GetIDs();
  }
  
}
