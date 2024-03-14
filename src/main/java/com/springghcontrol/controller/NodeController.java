package com.springghcontrol.controller;

import com.springghcontrol.model.HumiditySensor;
import com.springghcontrol.model.Node;
import com.springghcontrol.model.TemperatureSensor;
import com.springghcontrol.model.TemperatureStatistic;
import com.springghcontrol.repository.*;
import com.springghcontrol.service.NodeService;
import com.springghcontrol.util.Constant;
import com.springghcontrol.util.NodeJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/node")
public class NodeController {

    private final NodesRepository nodesRepository;
    private final TemperatureSensorRepository temperatureSensorRepository;
    private final HumiditySensorRepository humiditySensorRepository;
    private final HumidityStatisticRepository humidityStatisticRepository;
    private final TemperatureStatisticRepository temperatureStatisticRepository;

    private final NodeService nodeService;

    public NodeController(NodesRepository nodesRepository,
                          TemperatureSensorRepository temperatureSensorRepository, HumiditySensorRepository humiditySensorRepository,
                          HumidityStatisticRepository humidityStatisticRepository,
                          TemperatureStatisticRepository temperatureStatisticRepository, NodeService nodeService) {
        this.nodesRepository = nodesRepository;
        this.temperatureSensorRepository = temperatureSensorRepository;
        this.humiditySensorRepository = humiditySensorRepository;
        this.humidityStatisticRepository = humidityStatisticRepository;
        this.temperatureStatisticRepository = temperatureStatisticRepository;
        this.nodeService = nodeService;
    }


    @GetMapping
    public List<NodeJson> getSensors() {
        return nodeService.getAllNodes();
    }

    @GetMapping("/temperature")
    public List<TemperatureSensor> getTemperature() {
        return temperatureSensorRepository.findAll();
    }

    @GetMapping("/humidity")
    public List<HumiditySensor> getHumidity() {
        return humiditySensorRepository.findAll();
    }

    @GetMapping("/addData")
    public String addData() {
        Node node = new Node("192.168.1.90", "Test", "Test");
        Node node1 = new Node("192.168.1.91", "Test1", "Test1");
        TemperatureSensor temperatureSensor = new TemperatureSensor(node, Constant.TEMPERATURE_DH22, "Грунт");
        TemperatureSensor temperatureSensor1 = new TemperatureSensor(node, Constant.TEMPERATURE_ONEWIRE_A, "Повітря");
        TemperatureSensor temperatureSensor2 = new TemperatureSensor(node, Constant.TEMPERATURE_ONEWIRE_B, "Повітря зовні");

        TemperatureStatistic temperatureStatistic = new TemperatureStatistic(node, 11, 11, 11, 11, 11, 11);
        TemperatureStatistic temperatureStatistic1 = new TemperatureStatistic(node, 22, 22, 22, 22, 22, 22);
        TemperatureStatistic temperatureStatistic2 = new TemperatureStatistic(node, 33, 33, 33, 33, 33, 33);

        nodesRepository.saveAll(List.of(node, node1));
        temperatureSensorRepository.saveAll(List.of(temperatureSensor, temperatureSensor1, temperatureSensor2));
        temperatureStatisticRepository.saveAll(List.of(temperatureStatistic, temperatureStatistic1, temperatureStatistic2));
        nodeService.updateFromDb();
        return "Done";
    }

    @GetMapping("/get/{ip}")
    public ResponseEntity<NodeJson> getTemperature(@PathVariable("ip") String nodeIp) {
        Optional<NodeJson> nodeByIp = nodeService.getNodeByIp(nodeIp);
        return nodeByIp.map(nodeJson -> new ResponseEntity<>(nodeJson, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
