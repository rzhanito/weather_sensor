package ru.rzhanito.sensor.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rzhanito.sensor.entity.MeasurementEntity;
import ru.rzhanito.sensor.entity.SensorEntity;
import ru.rzhanito.sensor.exception.SensorNotFoundException;
import ru.rzhanito.sensor.model.Measurement;
import ru.rzhanito.sensor.model.Sensor;
import ru.rzhanito.sensor.repository.MeasurementRepo;
import ru.rzhanito.sensor.repository.SensorRepo;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepo measurementRepo;
    private final SensorRepo sensorRepo;
    private final RestTemplate restTemplate;

    public void add(MeasurementEntity measurement) throws SensorNotFoundException {
        SensorEntity sensor = sensorRepo.findByName(measurement.getSensor().getName());
        if(sensor != null) {
            measurement.setSensor(sensor);
            measurementRepo.save(measurement);
        }
        else{
            throw new SensorNotFoundException("Такой сенсор не найден.");
        }

    }

    public List<Measurement> get(){
        return measurementRepo.findAll().stream().map(Measurement::toModel).toList();
    }

    public Integer countRainingDays(){
        return measurementRepo.countAllByRainingIsTrue();
    }

    public void addRandomMeasurements(Integer howManyMeasurements, SensorEntity sensor){
        for (int i = 0; i < howManyMeasurements; i++){
            MeasurementEntity randomMeasurement = createRandomMeasurement(sensor);
            restTemplate.postForEntity("http://localhost:8080/measurements/add", randomMeasurement, String.class);
        }
    }

    public MeasurementEntity createRandomMeasurement(SensorEntity sensor){
        MeasurementEntity measurement = new MeasurementEntity();
        measurement.setValue(new Random().nextDouble() * 200 - 100);
        measurement.setRaining(new Random().nextBoolean());
        measurement.setSensor(sensor);

        return measurement;
    }
}
