package com.springghcontrol.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springghcontrol.model.HumiditySensor;
import com.springghcontrol.model.Node;
import com.springghcontrol.model.TemperatureSensor;
import com.springghcontrol.service.DBService;
import com.springghcontrol.service.NodeService;
import com.springghcontrol.util.Constant;
import com.springghcontrol.util.NodeJson;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService {

    private final DBService dbService;

    @Getter
    private final List<NodeJson> nodesList = new ArrayList<>();
    private final ObjectMapper om;


    public NodeServiceImpl(DBService dbService) {
        this.dbService = dbService;
        om = new ObjectMapper();
        updateFromDb();
    }

    @Override
    public void update() {
        for (NodeJson currentNode : nodesList) {
            Optional<NodeJson> nodeJsonObject = getNodeJsonObject(currentNode.getIp());
            nodeJsonObject.ifPresent(currentNode::update);
        }
    }

    @Override
    public void updateFromDb() {
        List<Node> nodes = dbService.getAllNodes();
        if (nodes != null && !nodes.isEmpty()) {
            for (Node node : nodes) {
                Optional<NodeJson> nodeJsonObject = getNodeJsonObject(node.getIp());
                nodeJsonObject.ifPresent(nodeJson -> nodesList.add(updateAllSensorsNames(nodeJson, node)));
            }
        }
        System.out.println();
    }


    @Override
    public List<NodeJson> getAllNodes() {
        return nodesList;
    }

    @Override
    public Optional<NodeJson> getNodeByIp(String ip) {
        for (NodeJson nodeJson : nodesList) {
            if (nodeJson.getIp().equals(ip)) {
                return Optional.of(nodeJson);
            }
        }
        return Optional.empty();
    }

    private NodeJson updateAllSensorsNames(NodeJson nodeJson, Node node) {
        List<TemperatureSensor> temperatureSensors = dbService.getTemperatureSensorsByNodeId(node);
        List<HumiditySensor> humiditySensors = dbService.getHumiditySensorsByNodeId(node);

        for (TemperatureSensor tempSensor : temperatureSensors) {
            for (NodeJson.Sensor s : nodeJson.getSensors().getTemperatureSensors().get()) {
                if (tempSensor.getSensorName().equals(s.getSensorName())) {
                    s.setName(tempSensor.getName());
                }
            }
        }
        for (HumiditySensor humiditySensor : humiditySensors) {
            for (NodeJson.Sensor s : nodeJson.getSensors().getTemperatureSensors().get()) {
                if (humiditySensor.getSensorName().equals(s.getSensorName())) {
                    s.setName(humiditySensor.getName());
                }
            }
        }
        return nodeJson;
    }

    private Optional<NodeJson> getNodeJsonObject(String ip) {
        Optional<URL> url = getUrl(ip);

        try {
            if (url.isPresent()) {
                HttpURLConnection con = (HttpURLConnection) url.get().openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(2 * 1000);
                NodeJson nodeJson = om.readValue(con.getInputStream(), NodeJson.class);
                nodeJson.setActive(true);
                return Optional.of(nodeJson);
            }
        } catch (IOException e) {
            log.warn(String.format("Node at %s is not accessible", ip));
        }
        try {
            NodeJson nodeJson = om.readValue(Constant.EMPTY_JSON, NodeJson.class);
            nodeJson.setIp(ip);
            nodeJson.setActive(false);
            return Optional.of(nodeJson);
        } catch (JsonProcessingException e) {
            log.error("Cannot parse json for node");
        }

        return Optional.empty();
    }

    private Optional<URL> getUrl(String ip) {
        try {
            return Optional.of(new URI(String.format("http://%s/sensors", ip)).toURL());
        } catch (MalformedURLException | URISyntaxException e) {
            log.error("Cannot build the url");
        }
        return Optional.empty();
    }
}
