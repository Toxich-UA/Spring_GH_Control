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
@Table(name = "teperatureStatistic")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureStatistic {

  @Id
  @SequenceGenerator(
      name = "temperatureStatistic_id_sequence",
      sequenceName = "temperatureStatistic_id_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "temperatureStatistic_id_sequence"
  )
  private Long id;

  @ManyToOne
  @JoinColumn(name = "nodeId")
  private Node nodeId;

  private Integer dh22;

  private Integer oneWire1;

  private Integer oneWire2;

  private Integer oneWire3;

  private Integer oneWire4;

  private Integer oneWire5;

  public TemperatureStatistic(Node nodeId, Integer dh22, Integer oneWire1, Integer oneWire2,
      Integer oneWire3, Integer oneWire4, Integer oneWire5) {
    this.nodeId = nodeId;
    this.dh22 = dh22;
    this.oneWire1 = oneWire1;
    this.oneWire2 = oneWire2;
    this.oneWire3 = oneWire3;
    this.oneWire4 = oneWire4;
    this.oneWire5 = oneWire5;
  }
}
