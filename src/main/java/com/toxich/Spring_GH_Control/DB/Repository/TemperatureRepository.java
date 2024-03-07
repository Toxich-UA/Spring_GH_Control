package com.toxich.Spring_GH_Control.DB.Repository;

import com.toxich.Spring_GH_Control.DB.Model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {
    List<Temperature> findByNodeId(Integer NodeId);
}
