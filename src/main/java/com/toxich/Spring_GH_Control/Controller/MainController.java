package com.toxich.Spring_GH_Control.Controller;

import com.toxich.Spring_GH_Control.DB.Model.HumiditySensor;
import com.toxich.Spring_GH_Control.DB.Model.Node;
import com.toxich.Spring_GH_Control.DB.Model.TemperatureSensor;
import com.toxich.Spring_GH_Control.DB.Model.TemperatureStatistic;
import com.toxich.Spring_GH_Control.DB.Repository.HumiditySensorRepository;
import com.toxich.Spring_GH_Control.DB.Repository.HumidityStatisticRepository;
import com.toxich.Spring_GH_Control.DB.Repository.NodesRepository;
import com.toxich.Spring_GH_Control.DB.Repository.TemperatureSensorRepository;
import com.toxich.Spring_GH_Control.DB.Repository.TemperatureStatisticRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class MainController {

    private final NodesRepository nodesRepository;
    private final TemperatureSensorRepository temperatureSensorRepository;
    private final HumiditySensorRepository humiditySensorRepository;
    private final HumidityStatisticRepository humidityStatisticRepository;
    private final TemperatureStatisticRepository temperatureStatisticRepository;

  public MainController(NodesRepository nodesRepository,
      TemperatureSensorRepository temperatureSensorRepository, HumiditySensorRepository humiditySensorRepository,
      HumidityStatisticRepository humidityStatisticRepository,
      TemperatureStatisticRepository temperatureStatisticRepository) {
    this.nodesRepository = nodesRepository;
    this.temperatureSensorRepository = temperatureSensorRepository;
    this.humiditySensorRepository = humiditySensorRepository;
    this.humidityStatisticRepository = humidityStatisticRepository;
    this.temperatureStatisticRepository = temperatureStatisticRepository;
  }


  @GetMapping
    public List<Node> getSensors() {
        return nodesRepository.findAll();
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
        TemperatureSensor temperatureSensor = new TemperatureSensor(node, "DH22_temperature", "Грунт");
        TemperatureSensor temperatureSensor1 = new TemperatureSensor(node, "OneWire_a", "Повітря");
        TemperatureSensor temperatureSensor2 = new TemperatureSensor(node, "OneWire_b", "Повітря зовні");

        TemperatureStatistic temperatureStatistic = new TemperatureStatistic(node, 11, 11, 11, 11, 11, 11);
        TemperatureStatistic temperatureStatistic1 = new TemperatureStatistic(node, 22, 22, 22, 22, 22, 22);
        TemperatureStatistic temperatureStatistic2 = new TemperatureStatistic(node, 33, 33, 33, 33, 33, 33);

        nodesRepository.saveAll(List.of(node, node1));
        temperatureSensorRepository.saveAll(List.of(temperatureSensor, temperatureSensor1, temperatureSensor2));
        temperatureStatisticRepository.saveAll(List.of(temperatureStatistic, temperatureStatistic1, temperatureStatistic2));
        return "Done";
    }

    @GetMapping("/get")
    public List<TemperatureSensor> getTemperature(@RequestParam("id") Node nodeId) {
        return temperatureSensorRepository.findByNodeId(nodeId);
    }

}
