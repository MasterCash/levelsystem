package io.github.mastercash.levelsystem.components.base;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface StatComponent extends Component {
  /**
   * gets the stat value
   * @return
   */
  float getValue();
  float Increment(float value);
  float Decrement(float value);
  /**
   * key for this stat component
   */
  static String key = "";
}