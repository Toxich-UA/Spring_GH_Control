package com.springghcontrol.controller;

import com.springghcontrol.repository.HumidityStatisticRepository;
import com.springghcontrol.repository.TemperatureStatisticRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistic")
public class StatisticController {

    private final HumidityStatisticRepository humidityStatisticRepository;
    private final TemperatureStatisticRepository temperatureStatisticRepository;


    public StatisticController(HumidityStatisticRepository humidityStatisticRepository, TemperatureStatisticRepository temperatureStatisticRepository) {
        this.humidityStatisticRepository = humidityStatisticRepository;
        this.temperatureStatisticRepository = temperatureStatisticRepository;
    }


}
