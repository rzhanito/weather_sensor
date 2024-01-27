package ru.rzhanito.sensor.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.sensor.entity.MeasurementEntity;
import ru.rzhanito.sensor.entity.SensorEntity;
import ru.rzhanito.sensor.exception.SensorNotFoundException;
import ru.rzhanito.sensor.model.Measurement;
import ru.rzhanito.sensor.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("measurements")
@Data
@AllArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@Valid @RequestBody MeasurementEntity measurement){
        try {
            measurementService.add(measurement);
            return ResponseEntity.ok().body("Измерение успешно добавлено.");
        }catch (SensorNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла непонятная ошибка.");
        }
    }

    @PostMapping("/addRandomMeasurements")
    public ResponseEntity<String> addRandomMeasurements(@Valid @RequestBody SensorEntity sensor, @RequestParam Integer randomMeasurementsAmount){
        try{
            measurementService.addRandomMeasurements(randomMeasurementsAmount, sensor);
            return ResponseEntity.ok().body(randomMeasurementsAmount + " случайных измерений от сенсора " + sensor.getName() + " было успешно добавлено.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Measurement>> get(){
        List<Measurement> measurements = measurementService.get();

        return ResponseEntity.ok(measurements);

    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity countRainyDays(){
        try {
            return ResponseEntity.ok(measurementService.countRainingDays());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
