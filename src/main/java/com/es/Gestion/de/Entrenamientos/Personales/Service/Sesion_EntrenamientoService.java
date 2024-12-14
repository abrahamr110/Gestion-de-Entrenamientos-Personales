package com.es.Gestion.de.Entrenamientos.Personales.Service;

import com.es.Gestion.de.Entrenamientos.Personales.DTO.Sesion_EntrenamientoDTO;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Sesion_Entrenamiento;
import com.es.Gestion.de.Entrenamientos.Personales.util.Mapper;
import com.es.Gestion.de.Entrenamientos.Personales.util.Sesion_EntrenamientoValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.es.Gestion.de.Entrenamientos.Personales.Repository.Sesion_EntrenamientoRepository;
import java.util.List;


@Service
public class Sesion_EntrenamientoService {
    @Autowired
    private Sesion_EntrenamientoRepository sesionRepository;

    @Autowired
    private Sesion_EntrenamientoValidacion sesionValidacion;

    public Sesion_EntrenamientoDTO getAll() {
        List<Sesion_Entrenamiento> sesiones = sesionRepository.findAll();
        if (sesiones.isEmpty()) {
            throw new IllegalArgumentException("No hay sesiones de entrenamiento registradas");
        }
        return Mapper.toDTOSesion(sesiones.get(0));
    }

    public Sesion_EntrenamientoDTO getSesionById(Long id) {
        sesionValidacion.validateSesionDeletion(id);
        Sesion_Entrenamiento sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sesión de entrenamiento no encontrada para el ID: " + id));
        return Mapper.toDTOSesion(sesion);
    }

    public Sesion_EntrenamientoDTO createSesion(Sesion_EntrenamientoDTO sesionDTO) {
        sesionValidacion.validateSesionCreation(sesionDTO);
        Sesion_Entrenamiento sesion = new Sesion_Entrenamiento(
                sesionDTO.getFecha(),
                sesionDTO.getDuracion(),
                sesionDTO.getDescripcion()
        );
        Sesion_Entrenamiento savedSesion = sesionRepository.save(sesion);
        return Mapper.toDTOSesion(savedSesion);
    }

    public Sesion_EntrenamientoDTO updateSesion(Long id, Sesion_EntrenamientoDTO sesionDTO) {
        sesionValidacion.validateSesionUpdate(id, sesionDTO);
        Sesion_Entrenamiento existingSesion = sesionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sesión de entrenamiento no encontrada para el ID: " + id));
        if (sesionDTO.getFecha() != null) {
            existingSesion.setFecha(sesionDTO.getFecha());
        }
        if (sesionDTO.getDuracion() != null) {
            existingSesion.setDuracion(sesionDTO.getDuracion());
        }
        if (sesionDTO.getDescripcion() != null) {
            existingSesion.setDescripcion(sesionDTO.getDescripcion());
        }
        Sesion_Entrenamiento updatedSesion = sesionRepository.save(existingSesion);
        return Mapper.toDTOSesion(updatedSesion);
    }

    public void deleteSesion(Long id) {
        sesionValidacion.validateSesionDeletion(id);
        Sesion_Entrenamiento sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sesión de entrenamiento no encontrada para el ID: " + id));
        sesionRepository.delete(sesion);
    }
}
