package com.springghcontrol.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Service
public class NodeJson {

  String ip;
  Sensors sensors;
  Status status;

  @JsonProperty("ip")
  public String getIp() {
    return this.ip;
  }

  @JsonProperty("sensors")
  public Sensors getSensors() {
    return this.sensors;
  }

  @JsonProperty("status")
  public Status getStatus() {
    return this.status;
  }

  @NoArgsConstructor
  @Data
  public static class SensorsObject {

    Sensor a;
    Sensor b;
    Sensor c;
    Sensor d;
    Sensor e;
    Sensor f;

  }

  @NoArgsConstructor
  @Setter
  public static class Sensors {

    SensorsObject temperatureSensors;
    SensorsObject humiditySensors;

    @JsonProperty("temperature")
    public SensorsObject getTemperatureSensors() {
      return this.temperatureSensors;
    }

    @JsonProperty("humidity")
    public SensorsObject getHumiditySensors() {
      return this.humiditySensors;
    }

  }

  @NoArgsConstructor
  @Setter
  public static class Status {

    int pump;
    int fans;
    int lamps;

    @JsonProperty("fans")
    public int getFans() {
      return this.fans;
    }

    @JsonProperty("pump")
    public int getPump() {
      return this.pump;
    }

    @JsonProperty("lamps")
    public int getLamps() {
      return this.lamps;
    }

  }

}
