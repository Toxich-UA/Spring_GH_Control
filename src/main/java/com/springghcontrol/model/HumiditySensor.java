package com.springghcontrol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HumiditySensors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HumiditySensor {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nodeId")
    @JsonBackReference
    private Node nodeId;

    private String sensorName;

    private String name;

    public HumiditySensor(Node nodeId, String sensorName, String name) {
        this.nodeId = nodeId;
        this.sensorName = sensorName;
        this.name = name;
    }
}
