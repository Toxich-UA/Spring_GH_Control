package com.toxich.Spring_GH_Control.DB.Repository;

import com.toxich.Spring_GH_Control.DB.Model.HumiditySensor;
import com.toxich.Spring_GH_Control.DB.Model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumiditySensorRepository extends JpaRepository<HumiditySensor, Integer> {
    List<HumiditySensor> findByNodeId(Node nodeId);
}
