package ru.rzhanito.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rzhanito.sensor.entity.SensorEntity;

@Repository
public interface SensorRepo extends JpaRepository<SensorEntity, Long> {
    SensorEntity findByName(String name);
}
