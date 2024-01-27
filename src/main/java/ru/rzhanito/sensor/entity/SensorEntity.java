package ru.rzhanito.sensor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "sensor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    @NotNull(message = "Название не может быть null")
    @Size(min = 3, max = 30, message = "Название сенсора должно быть в диапазоне 3-30 символов.")
    private String name;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<MeasurementEntity> measurements;
}
