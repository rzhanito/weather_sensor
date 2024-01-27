package ru.rzhanito.sensor.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rzhanito.sensor.entity.SensorEntity;
import ru.rzhanito.sensor.exception.SensorAlreadyExistsException;
import ru.rzhanito.sensor.exception.SensorNotFoundException;
import ru.rzhanito.sensor.service.SensorService;

@RestController
@RequestMapping("sensors")
@AllArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/registration")
    public ResponseEntity<String> addSensor(@RequestBody @Valid SensorEntity sensor) {
        try {
            sensorService.addSensor(sensor);
            return ResponseEntity.ok().body("Сенсор успешно добавлен");
        } catch (SensorAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Произошла непонятная ошибка.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSensor(@RequestParam Long id) {
        try {
            SensorEntity sensor = sensorService.deleteSensor(id);
            return ResponseEntity.ok().body("Сенсор " + sensor.getName() + " успешно удалён.");
        } catch (SensorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла непонятная ошибка.");
        }
    }
}
