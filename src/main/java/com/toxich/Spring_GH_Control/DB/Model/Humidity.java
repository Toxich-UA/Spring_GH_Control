package com.toxich.Spring_GH_Control.DB.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Humidity")
public class Humidity {

    @Id
    @SequenceGenerator(
            name = "humidity_id_sequence",
            sequenceName = "humidity_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "humidity_id_sequence"
    )
    private Integer id;

    private Integer nodeId;
    private Integer dh22;
    private Integer sensor1;
    private Integer sensor2;
    private Integer sensor3;
    private Integer sensor4;
    private Integer sensor5;

    public Humidity() {
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Humidity(Integer nodeId, Integer dh22, Integer sensor_1, Integer sensor_2, Integer sensor_3, Integer sensor_4, Integer sensor_5) {
        this.nodeId = nodeId;
        this.dh22 = dh22;
        sensor1 = sensor_1;
        sensor2 = sensor_2;
        sensor3 = sensor_3;
        sensor4 = sensor_4;
        sensor5 = sensor_5;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "Id=" + id +
                ", DH_22=" + dh22 +
                ", Sensor_1=" + sensor1 +
                ", Sensor_2=" + sensor2 +
                ", Sensor_3=" + sensor3 +
                ", Sensor_4=" + sensor4 +
                ", Sensor_5=" + sensor5 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Humidity humidity = (Humidity) o;
        return Objects.equals(id, humidity.id) && Objects.equals(dh22, humidity.dh22) && Objects.equals(sensor1, humidity.sensor1) && Objects.equals(sensor2, humidity.sensor2) && Objects.equals(sensor3, humidity.sensor3) && Objects.equals(sensor4, humidity.sensor4) && Objects.equals(sensor5, humidity.sensor5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dh22, sensor1, sensor2, sensor3, sensor4, sensor5);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDh22() {
        return dh22;
    }

    public void setDh22(Integer dh22) {
        this.dh22 = dh22;
    }

    public Integer getSensor1() {
        return sensor1;
    }

    public void setSensor1(Integer sensor1) {
        this.sensor1 = sensor1;
    }

    public Integer getSensor2() {
        return sensor2;
    }

    public void setSensor2(Integer sensor2) {
        this.sensor2 = sensor2;
    }

    public Integer getSensor3() {
        return sensor3;
    }

    public void setSensor3(Integer sensor3) {
        this.sensor3 = sensor3;
    }

    public Integer getSensor4() {
        return sensor4;
    }

    public void setSensor4(Integer sensor4) {
        this.sensor4 = sensor4;
    }

    public Integer getSensor5() {
        return sensor5;
    }

    public void setSensor5(Integer sensor5) {
        this.sensor5 = sensor5;
    }
}
