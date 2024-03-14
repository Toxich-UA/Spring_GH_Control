package com.springghcontrol.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Service
public class NodeJson {

    String ip;
    Sensors sensors;
    Status peripheralsStatus;
    boolean active;

    @JsonProperty("ip")
    public String getIp() {
        return this.ip;
    }

    @JsonProperty("sensors")
    public Sensors getSensors() {
        return this.sensors;
    }

    @JsonProperty("status")
    public Status getPeripheralsStatus() {
        return this.peripheralsStatus;
    }

    @JsonProperty("active")
    public boolean getActiveStatus() {
        return this.active;
    }

    public void update(NodeJson newNodeData) {
        updateNames(this.sensors, newNodeData.getSensors());
        this.sensors = newNodeData.getSensors();
        this.peripheralsStatus = newNodeData.getPeripheralsStatus();
    }


    private void updateNames(Sensors currentNode, Sensors newNode) {
        for (Sensor sensor : newNode.getTemperatureSensors().get()) {
            Optional<Sensor> sensorBySensorName = currentNode.getTemperatureSensors().getSensorBySensorName(sensor.getSensorName());
            sensorBySensorName.ifPresent(value -> sensor.setName(value.getName()));
        }
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

        public List<Sensor> get() {
            return List.of(a, b, c, d, e, f);
        }

        public Optional<Sensor> getSensorBySensorName(String name) {
            if (name != null) {
                for (Sensor sensor : this.get()) {

                    if (sensor.getSensorName().equals(name)) {
                        return Optional.of(sensor);
                    }
                }
            }
            return Optional.empty();
        }
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

    @NoArgsConstructor
    @Setter
    public static class Sensor {
        int val;
        int change;
        String sensorName;
        String name;

        @JsonProperty("val")
        public int getVal() {
            return this.val;
        }

        @JsonProperty("change")
        public int getChange() {
            return this.change;
        }

        @JsonProperty("name")
        public String getName() {
            return this.name;
        }

        @JsonProperty("sensorName")
        public String getSensorName() {
            return this.sensorName;
        }

    }

}
