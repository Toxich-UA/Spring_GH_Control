package com.springghcontrol.repository;

import com.springghcontrol.model.Node;
import com.springghcontrol.model.TemperatureSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensor, Integer> {
    List<TemperatureSensor> findByNodeId(Node node);
}
