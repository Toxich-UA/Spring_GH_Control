package com.springghcontrol.service.impl;

import com.springghcontrol.model.HumiditySensor;
import com.springghcontrol.model.Node;
import com.springghcontrol.model.TemperatureSensor;
import com.springghcontrol.repository.HumiditySensorRepository;
import com.springghcontrol.repository.NodesRepository;
import com.springghcontrol.repository.TemperatureSensorRepository;
import com.springghcontrol.service.DBService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    private final NodesRepository nodesRepository;
    private final TemperatureSensorRepository temperatureSensorRepository;
    private final HumiditySensorRepository humiditySensorRepository;

    public DBServiceImpl(NodesRepository nodesRepository, TemperatureSensorRepository temperatureSensorRepository, HumiditySensorRepository humiditySensorRepository) {
        this.nodesRepository = nodesRepository;
        this.temperatureSensorRepository = temperatureSensorRepository;
        this.humiditySensorRepository = humiditySensorRepository;
    }

    @Override
    public List<Node> getAllNodes() {
        return nodesRepository.findAll();
    }

    @Override
    public Node getNodeByIp(String ip) {
        return nodesRepository.getNodeByIp(ip);
    }

    @Override
    public List<TemperatureSensor> getAllTemperatureSensors() {
        return temperatureSensorRepository.findAll();
    }

    @Override
    public List<TemperatureSensor> getTemperatureSensorsByNodeId(Node node) {
        return temperatureSensorRepository.findByNodeId(node);
    }

    @Override
    public List<HumiditySensor> getAllHumiditySensors() {
        return humiditySensorRepository.findAll();
    }

    @Override
    public List<HumiditySensor> getHumiditySensorsByNodeId(Node node) {
        return humiditySensorRepository.findByNodeId(node);
    }

}
