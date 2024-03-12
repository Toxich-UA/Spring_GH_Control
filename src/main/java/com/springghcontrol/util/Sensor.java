package com.springghcontrol.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensor {
  int val;
  int change;

  @JsonProperty("val")
  public int getVal() {
    return this.val;
  }

  @JsonProperty("change")
  public int getChange() {
    return this.change;
  }

}
