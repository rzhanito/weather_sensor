package ru.rzhanito.sensor.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.rzhanito.sensor.entity.SensorEntity;
import ru.rzhanito.sensor.exception.SensorAlreadyExistsException;
import ru.rzhanito.sensor.exception.SensorNotFoundException;
import ru.rzhanito.sensor.repository.SensorRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorRepo sensorRepo;

    public void addSensor(SensorEntity sensor) throws SensorAlreadyExistsException {
        if(sensorRepo.findByName(sensor.getName()) != null)
            throw new SensorAlreadyExistsException("Такой сенсор уже существует.");
        else {
            sensorRepo.save(sensor);
        }

    }

    public SensorEntity deleteSensor(Long id) throws SensorNotFoundException{
        Optional<SensorEntity> sensor = sensorRepo.findById(id);
        if(sensor.isPresent()){
            sensorRepo.delete(sensor.get());
            return sensor.get();
        } else
            throw new SensorNotFoundException("Сенсор с ID " + id + " не найден.");
    }

}
