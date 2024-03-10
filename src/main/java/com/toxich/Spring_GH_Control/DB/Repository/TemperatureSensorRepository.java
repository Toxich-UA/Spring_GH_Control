package com.toxich.Spring_GH_Control.DB.Repository;

import com.toxich.Spring_GH_Control.DB.Model.Node;
import com.toxich.Spring_GH_Control.DB.Model.TemperatureSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperatureSensorRepository extends JpaRepository<TemperatureSensor, Integer> {
    List<TemperatureSensor> findByNodeId(Node nodeId);
}
