package com.toxich.Spring_GH_Control.DB.Repository;

import com.toxich.Spring_GH_Control.DB.Model.HumiditySensor;
import com.toxich.Spring_GH_Control.DB.Model.HumidityStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumidityStatisticRepository extends JpaRepository<HumidityStatistic, Long> {


}
