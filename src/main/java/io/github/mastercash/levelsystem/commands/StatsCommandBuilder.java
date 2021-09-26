package io.github.mastercash.levelsystem.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import io.github.mastercash.levelsystem.commands.suggestions.StatSuggestions;
import io.github.mastercash.levelsystem.components.StatConstants;
import io.github.mastercash.levelsystem.utils.Stat;
import io.github.mastercash.levelsystem.utils.Stats;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class StatsCommandBuilder {
  public static String CMD = "stats";
  
  public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean _dedicated) {
    dispatcher.register(
      literal(CMD).then(argument("stat", StringArgumentType.word())
                    .suggests(new StatSuggestions()).executes(StatsCommandBuilder::getLevel))
                  .executes(StatsCommandBuilder::getLevels)
    );
  }

  private static int getLevel(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
    var source = context.getSource();
    // entertain the idea of non-player entities having stats
    var caller = source.getPlayer();
    var statArg = StringArgumentType.getString(context, "stat");
    
    var id = StatConstants.GetStatID(statArg);
    if (!Stat.Exists(id, caller)) throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().create();
    var level = Stat.Get(id, caller);
    var message = new LiteralText(statArg + ": " + Float.toString(level));
    source.getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, caller.getUuid());

    return 1;
  }

  private static int getLevels(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
    var source = context.getSource();
    var caller = source.getPlayer();
    var message = new LiteralText("");
    for(var stat : Stats.GetIDs()) {
      var id = StatConstants.GetStatID(stat);
      var level = Stat.Get(id, caller);
      message.append(stat+": "+Float.toString(level)+"\n");
    }
    source.getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, caller.getUuid());
    return 1;
  }
}
