package com.springghcontrol.controller;

import com.springghcontrol.model.HumiditySensor;
import com.springghcontrol.model.TemperatureSensor;
import com.springghcontrol.repository.HumiditySensorRepository;
import com.springghcontrol.repository.TemperatureSensorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sensors")
public class SensorsController {
    private final TemperatureSensorRepository temperatureSensorRepository;
    private final HumiditySensorRepository humiditySensorRepository;


    public SensorsController(TemperatureSensorRepository temperatureSensorRepository, HumiditySensorRepository humiditySensorRepository) {
        this.temperatureSensorRepository = temperatureSensorRepository;
        this.humiditySensorRepository = humiditySensorRepository;
    }

    @GetMapping("/temperature")
    public List<TemperatureSensor> getTemperature() {
        return temperatureSensorRepository.findAll();
    }

    @GetMapping("/humidity")
    public List<HumiditySensor> getHumidity() {
        return humiditySensorRepository.findAll();
    }

}
