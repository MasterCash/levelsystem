package io.github.mastercash.levelsystem.components.base;

import io.github.mastercash.levelsystem.utils.Stats;
import net.minecraft.nbt.NbtCompound;

/**
 * TODO: Comment
 */
public final class StatComponentImpl implements StatComponent {
  private String key = "";
  private float value = (float) 0;

  public StatComponentImpl(String key) {
    this.key = key;
  }
  public StatComponentImpl(Stats stat) {
    this.key = stat.getID();
  }

  @Override 
  public float getValue() { 
    return this.value;
  }

  @Override
  public void readFromNbt(NbtCompound tag) {
    this.value = tag.getFloat(key);
  }

  @Override
  public void writeToNbt(NbtCompound tag) {
    tag.putFloat(key, this.value);
  }

  @Override
  public float Increment(float value) {
    this.value += value;
    return this.value;
  }

  @Override
  public float Decrement(float value) {
    this.value -= value;
    return this.value;
  }

}