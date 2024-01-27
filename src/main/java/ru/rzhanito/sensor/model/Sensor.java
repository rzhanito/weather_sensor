package ru.rzhanito.sensor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rzhanito.sensor.entity.SensorEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    public Sensor(SensorEntity sensor){
        this.name = sensor.getName();

    }
    public static Sensor toModel(SensorEntity sensor){
        Sensor model = new Sensor();
        model.setName(sensor.getName());
        model.setMeasurements(
                sensor.getMeasurements().stream()
                        .map(Measurement::toModel)
                        .collect(Collectors.toList())
        );
        return model;
    }
    private String name;
    private List<Measurement> measurements;
}
