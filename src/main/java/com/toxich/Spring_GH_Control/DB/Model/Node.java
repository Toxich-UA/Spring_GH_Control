package com.toxich.Spring_GH_Control.DB.Model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Nodes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public Node(String ip, String name, String description) {
        this.ip = ip;
        this.name = name;
        this.description = description;
    }
}
