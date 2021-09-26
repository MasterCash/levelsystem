package io.github.mastercash.levelsystem.commands.suggestions;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;

public abstract class CommandSuggestions implements SuggestionProvider<ServerCommandSource> {
  public abstract List<String> suggestions();
  
  @Override
  public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
    CommandSource.suggestMatching(this.suggestions(), builder);
    return builder.buildFuture();
  }
}
