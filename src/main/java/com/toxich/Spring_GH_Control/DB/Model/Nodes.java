package com.toxich.Spring_GH_Control.DB.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Nodes")
public class Nodes {

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

    public Nodes() {
    }

    public Nodes(String ip, String name, String description) {
        this.ip = ip;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "Id=" + id +
                ", IP='" + ip + '\'' +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodes nodes = (Nodes) o;
        return Objects.equals(id, nodes.id) && Objects.equals(ip, nodes.ip) && Objects.equals(name, nodes.name) && Objects.equals(description, nodes.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, name, description);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
