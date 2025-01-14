package com.espe.habitacion.repositories;

import org.springframework.data.repository.CrudRepository;
import com.espe.habitacion.models.entities.Habitacion;

public interface HabitacionRepository extends CrudRepository<Habitacion, Long> {
}
