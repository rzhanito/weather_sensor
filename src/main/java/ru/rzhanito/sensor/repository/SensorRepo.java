package ru.rzhanito.sensor.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rzhanito.sensor.entity.SensorEntity;

import java.util.List;

@Repository
public interface SensorRepo extends JpaRepository<SensorEntity, Long> {
    SensorEntity findByName(String name);
}
