package com.springghcontrol.service;

import com.springghcontrol.model.HumiditySensor;
import com.springghcontrol.model.Node;
import com.springghcontrol.model.TemperatureSensor;

import java.util.List;

public interface DBService {

    List<Node> getAllNodes();

    Node getNodeByIp(String ip);

    List<TemperatureSensor> getAllTemperatureSensors();

    List<TemperatureSensor> getTemperatureSensorsByNodeId(Node node);

    List<HumiditySensor> getAllHumiditySensors();

    List<HumiditySensor> getHumiditySensorsByNodeId(Node node);

}
