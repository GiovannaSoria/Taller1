package com.espe.habitacion.services;

import com.espe.habitacion.models.entities.Habitacion;
import com.espe.habitacion.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    @Override
    public List<Habitacion> findAll() {
        return (List<Habitacion>) repository.findAll();
    }

    @Override
    public Optional<Habitacion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Habitacion save(Habitacion habitacion) {
        return repository.save(habitacion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
