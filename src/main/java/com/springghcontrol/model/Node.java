package com.springghcontrol.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Nodes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Node {
    @Id
    @SequenceGenerator(
            name = "node_id_sequence",
            sequenceName = "node_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "node_id_sequence"
    )
    private Integer id;
    private String ip;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nodeId")
    @JsonManagedReference
    private List<TemperatureSensor> temperatureSensors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nodeId")
    @JsonManagedReference
    private List<HumiditySensor> humiditySensors;

    public Node(String ip, String name, String description) {
        this.ip = ip;
        this.name = name;
        this.description = description;
    }
}
