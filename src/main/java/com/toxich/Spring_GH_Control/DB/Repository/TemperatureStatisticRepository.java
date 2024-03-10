package com.toxich.Spring_GH_Control.DB.Repository;

import com.toxich.Spring_GH_Control.DB.Model.TemperatureSensor;
import com.toxich.Spring_GH_Control.DB.Model.TemperatureStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureStatisticRepository extends JpaRepository<TemperatureStatistic, Long> {

}
