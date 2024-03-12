package com.springghcontrol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TemperatureSensors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nodeId")
    @JsonBackReference
    private Node nodeId;

    private String sensorName;

    private String name;

    public TemperatureSensor(Node nodeId, String sensorName, String name) {
        this.nodeId = nodeId;
        this.sensorName = sensorName;
        this.name = name;
    }
}
