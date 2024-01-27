package ru.rzhanito.sensor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "measurements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = -100, message = "Минимальное значение: -100")
    @Max(value = 100, message = "Максимальное значение: 100")
    @NotNull(message = "Значение не может быть null")
    private Double value;
    @NotNull(message = "Значение не может быть null")
    private Boolean raining;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sensor_id")
    @NotNull(message = "Нужно указать какой сенсор отправил эти измерения")
    private SensorEntity sensor;

}
