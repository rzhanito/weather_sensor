package ru.rzhanito.sensor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rzhanito.sensor.entity.MeasurementEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    public static Measurement toModel(MeasurementEntity measurement){
        Measurement model = new Measurement();

        model.setValue(measurement.getValue());
        model.setRaining(measurement.getRaining());
        model.setSensor(measurement.getSensor().getName());

        return model;
    }
    private Double value;
    private Boolean raining;
    private String sensor;
}
