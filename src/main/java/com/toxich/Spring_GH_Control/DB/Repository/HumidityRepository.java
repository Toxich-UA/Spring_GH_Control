package com.toxich.Spring_GH_Control.DB.Repository;

import com.toxich.Spring_GH_Control.DB.Model.Humidity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumidityRepository extends JpaRepository<Humidity, Integer> {
    List<Humidity> findByNodeId(Integer NodeId);
}