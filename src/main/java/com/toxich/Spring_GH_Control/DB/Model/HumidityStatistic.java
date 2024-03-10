package com.toxich.Spring_GH_Control.DB.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "humidityStatistic")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HumidityStatistic {

  @Id
  @SequenceGenerator(
      name = "humidityStatistic_id_sequence",
      sequenceName = "humidityStatistic_id_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "humidityStatistic_id_sequence"
  )
  private Long id;

  @ManyToOne
  @JoinColumn(name = "nodeId")
  private Node nodeId;

  private Integer dh22;

  private Integer sensor1;

  private Integer sensor2;

  private Integer sensor3;

  private Integer sensor4;

  private Integer sensor5;

  public HumidityStatistic(Node nodeId, Integer dh22, Integer sensor1, Integer sensor2,
      Integer sensor3, Integer sensor4, Integer sensor5) {
    this.nodeId = nodeId;
    this.dh22 = dh22;
    this.sensor1 = sensor1;
    this.sensor2 = sensor2;
    this.sensor3 = sensor3;
    this.sensor4 = sensor4;
    this.sensor5 = sensor5;
  }
}
