package ru.rzhanito.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rzhanito.sensor.entity.MeasurementEntity;

import java.util.List;

@Repository
public interface MeasurementRepo extends JpaRepository<MeasurementEntity, Long> {
    List<MeasurementEntity> findAll();
    Integer countAllByRainingIsTrue();
}
