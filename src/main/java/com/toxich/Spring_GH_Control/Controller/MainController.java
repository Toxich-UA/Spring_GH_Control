package com.toxich.Spring_GH_Control.Controller;

import com.toxich.Spring_GH_Control.DB.Model.Humidity;
import com.toxich.Spring_GH_Control.DB.Model.Nodes;
import com.toxich.Spring_GH_Control.DB.Model.Temperature;
import com.toxich.Spring_GH_Control.DB.Repository.HumidityRepository;
import com.toxich.Spring_GH_Control.DB.Repository.NodesRepository;
import com.toxich.Spring_GH_Control.DB.Repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class MainController {
    @Autowired
    private final NodesRepository nodesRepository;
    @Autowired
    private final TemperatureRepository temperatureRepository;
    @Autowired
    private final HumidityRepository humidityRepository;

    public MainController(NodesRepository nodesRepository, TemperatureRepository temperatureRepository, HumidityRepository humidityRepository) {
        this.nodesRepository = nodesRepository;
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
    }

    @GetMapping
    public List<Nodes> getSensors() {
        return nodesRepository.findAll();
    }

    @GetMapping("/temperature")
    public List<Temperature> getTemperature() {
        return temperatureRepository.findAll();
    }

    @GetMapping("/humidity")
    public List<Humidity> getHumidity() {
        return humidityRepository.findAll();
    }

    @GetMapping("/addData")
    public String addData() {
        Nodes node = new Nodes("192.168.1.90", "Test", "Test");
        Nodes node1 = new Nodes("192.168.1.91", "Test1", "Test1");
        Temperature temperature = new Temperature(1, 11, 11, 11, 11, 11, 11);
        Temperature temperature1 = new Temperature(1, 22, 22, 22, 22, 22, 22);
        Temperature temperature2 = new Temperature(2, 33, 33, 33, 33, 33, 33);

        nodesRepository.save(node);
        nodesRepository.save(node1);
        temperatureRepository.save(temperature);
        temperatureRepository.save(temperature1);
        temperatureRepository.save(temperature2);
        return "Done";
    }

    @GetMapping("/get")
    public List<Temperature> getTemperature(@RequestParam("id") Integer nodeId) {
        return temperatureRepository.findByNodeId(nodeId);
    }

}
