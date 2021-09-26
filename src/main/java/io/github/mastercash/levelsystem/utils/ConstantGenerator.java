package io.github.mastercash.levelsystem.utils;

import java.util.List;

import io.github.mastercash.levelsystem.Constants;
import static io.github.mastercash.levelsystem.components.StatConstants.GetStatID;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;

public abstract class ConstantGenerator {
  public static void generateMinerBlocks() {
    var blocks = BlockTags.PICKAXE_MINEABLE.values();
    generateBlocks(blocks, GetStatID(Stats.MINER));
  }
  public static void generateLoggerBlocks() {
    var blocks = BlockTags.AXE_MINEABLE.values();
    generateBlocks(blocks, GetStatID(Stats.LOGGER));
  }
  public static void generateFarmerBlocks() {
    var blocks = BlockTags.HOE_MINEABLE.values();
    generateBlocks(blocks, GetStatID(Stats.FARMER));
  }
  public static float getExpValue(Block block) {
    return 1;
  }
  private static void generateBlocks(List<Block> blocks, Identifier id) {
    for(var block : blocks) {
      Constants.BlockToExpMap.put(block.asItem().toString(), getExpValue(block));
    }
  }

  public static void generate() {
    if(!Constants.Initialized) {
      generateMinerBlocks();
      generateFarmerBlocks();
      generateLoggerBlocks();
      Constants.Initialized = true;
    }
  }
}
