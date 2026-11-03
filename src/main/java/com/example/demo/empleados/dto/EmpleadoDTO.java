package com.example.demo.empleados.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class EmpleadoDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El email es obligatorio")
    private String email;

    @Past(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El estado civil es obligatorio")
    private String estadoCivil;

    @PositiveOrZero(message = "El sueldo debe de ser positivo y mayor que cero")
    private double sueldo;
}
