package com.espe.habitacion.controllers;

import com.espe.habitacion.models.entities.Habitacion;
import com.espe.habitacion.services.HabitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService service;

    // Crear una nueva habitación
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Habitacion habitacion, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(
                    err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(habitacion));
    }

    // Listar todas las habitaciones
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    // Buscar una habitación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Habitacion> habitacionOptional = service.findById(id);
        if (habitacionOptional.isPresent()) {
            return ResponseEntity.ok(habitacionOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Modificar una habitación por su ID
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@Valid @RequestBody Habitacion habitacion, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        Optional<Habitacion> habitacionOptional = service.findById(id);
        if (habitacionOptional.isPresent()) {
            Habitacion habitacionDB = habitacionOptional.get();
            habitacionDB.setNumero(habitacion.getNumero());
            habitacionDB.setTipo(habitacion.getTipo());
            habitacionDB.setCapacidad(habitacion.getCapacidad());
            habitacionDB.setFechaMantenimiento(habitacion.getFechaMantenimiento());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(habitacionDB));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una habitación por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Habitacion> habitacionOptional = service.findById(id);
        if (habitacionOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
