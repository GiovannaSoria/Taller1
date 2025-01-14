package com.espe.habitacion.services;

import com.espe.habitacion.models.entities.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionService {
    List<Habitacion> findAll();
    Optional<Habitacion> findById(Long id);
    Habitacion save(Habitacion habitacion);
    void deleteById(Long id);
}
