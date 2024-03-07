package com.toxich.Spring_GH_Control.DB.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Temperature")
public class Temperature {

    @Id
    @SequenceGenerator(
            name = "temperature_id_sequence",
            sequenceName = "temperature_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "temperature_id_sequence"
    )
    private Integer id;

    private Integer nodeId;
    private Integer dh22;
    private Integer oneWire1;
    private Integer oneWire2;
    private Integer oneWire3;
    private Integer oneWire4;
    private Integer oneWire5;

    public Temperature() {
    }

    public Temperature(Integer nodeId, Integer dh22, Integer oneWire_1, Integer oneWire_2, Integer oneWire_3, Integer oneWire_4, Integer oneWire_5) {
        this.nodeId = nodeId;
        this.dh22 = dh22;
        oneWire1 = oneWire_1;
        oneWire2 = oneWire_2;
        oneWire3 = oneWire_3;
        oneWire4 = oneWire_4;
        oneWire5 = oneWire_5;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "Id=" + id +
                ", DH_22=" + dh22 +
                ", OneWire_1=" + oneWire1 +
                ", OneWire_2=" + oneWire2 +
                ", OneWire_3=" + oneWire3 +
                ", OneWire_4=" + oneWire4 +
                ", OneWire_5=" + oneWire5 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(id, that.id) && Objects.equals(dh22, that.dh22) && Objects.equals(oneWire1, that.oneWire1) && Objects.equals(oneWire2, that.oneWire2) && Objects.equals(oneWire3, that.oneWire3) && Objects.equals(oneWire4, that.oneWire4) && Objects.equals(oneWire5, that.oneWire5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dh22, oneWire1, oneWire2, oneWire3, oneWire4, oneWire5);
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
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

    public Integer getOneWire1() {
        return oneWire1;
    }

    public void setOneWire1(Integer oneWire1) {
        this.oneWire1 = oneWire1;
    }

    public Integer getOneWire2() {
        return oneWire2;
    }

    public void setOneWire2(Integer oneWire2) {
        this.oneWire2 = oneWire2;
    }

    public Integer getOneWire3() {
        return oneWire3;
    }

    public void setOneWire3(Integer oneWire3) {
        this.oneWire3 = oneWire3;
    }

    public Integer getOneWire4() {
        return oneWire4;
    }

    public void setOneWire4(Integer oneWire4) {
        this.oneWire4 = oneWire4;
    }

    public Integer getOneWire5() {
        return oneWire5;
    }

    public void setOneWire5(Integer oneWire5) {
        this.oneWire5 = oneWire5;
    }
}
