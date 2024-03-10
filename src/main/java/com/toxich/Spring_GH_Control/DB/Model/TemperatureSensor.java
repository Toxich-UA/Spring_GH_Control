package com.toxich.Spring_GH_Control.DB.Model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TemperatureSensors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureSensor {

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

    @ManyToOne
    @JoinColumn(name = "nodeId")
    private Node nodeId;

    private String sensorName;

    private String name;

    public TemperatureSensor(Node nodeId, String sensorName, String name) {
        this.nodeId = nodeId;
        this.sensorName = sensorName;
        this.name = name;
    }
}
