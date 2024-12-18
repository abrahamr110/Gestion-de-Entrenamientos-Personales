package com.es.Gestion.de.Entrenamientos.Personales.util;

import com.es.Gestion.de.Entrenamientos.Personales.DTO.ProgresoDTO;
import com.es.Gestion.de.Entrenamientos.Personales.Repository.ProgresoRepository;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Progreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgresoValidacion {
    @Autowired
    private ProgresoRepository progresoRepository;

    public void validateGetProgreso(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del progreso debe ser un valor positivo.");
        }

        if (!progresoRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró un progreso con el ID proporcionado.");
        }
    }

    public void validateAddProgreso(ProgresoDTO progreso) {
        if (progreso == null) {
            throw new IllegalArgumentException("El objeto progreso no puede ser nulo.");
        }

        if (progreso.getPeso() == null || progreso.getPeso() <= 0) {
            throw new IllegalArgumentException("El peso debe ser un valor mayor a 0.");
        }

        if (progreso.getCalorias() == null || progreso.getCalorias() < 0) {
            throw new IllegalArgumentException("Las calorías deben ser un valor igual o mayor a 0.");
        }

        if (progreso.getObservaciones() == null || progreso.getObservaciones().trim().isEmpty()) {
            throw new IllegalArgumentException("Las observaciones no pueden estar vacías.");
        }
    }

    public void validateUpdateProgreso(Long id, ProgresoDTO progreso) {
        validateGetProgreso(id);

        validateAddProgreso(progreso);
    }

    public void validateDeleteProgreso(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del progreso debe ser un valor positivo.");
        }

        if (!progresoRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró un progreso con el ID proporcionado.");
        }
    }
}
