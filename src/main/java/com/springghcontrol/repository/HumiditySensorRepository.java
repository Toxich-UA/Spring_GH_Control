package com.springghcontrol.repository;

import com.springghcontrol.model.HumiditySensor;
import com.springghcontrol.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumiditySensorRepository extends JpaRepository<HumiditySensor, Integer> {
    List<HumiditySensor> findByNodeId(Node nodeId);
}
