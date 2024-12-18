package com.es.Gestion.de.Entrenamientos.Personales.Service;

import com.es.Gestion.de.Entrenamientos.Personales.DTO.Plan_EntrenamientoDTO;
import com.es.Gestion.de.Entrenamientos.Personales.DTO.ProgresoDTO;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Plan_Entrenamiento;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Progreso;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Sesion_Entrenamiento;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Usuario;
import com.es.Gestion.de.Entrenamientos.Personales.Repository.Sesion_EntrenamientoRepository;
import com.es.Gestion.de.Entrenamientos.Personales.Repository.UsuarioRepository;
import com.es.Gestion.de.Entrenamientos.Personales.util.Mapper;
import com.es.Gestion.de.Entrenamientos.Personales.util.ProgresoValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.es.Gestion.de.Entrenamientos.Personales.Repository.ProgresoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgresoService {
    @Autowired
    private ProgresoRepository progresoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio para buscar el cliente (usuario)

    @Autowired
    private Sesion_EntrenamientoRepository sesionRepository;

    @Autowired
    private ProgresoValidacion progresoValidacion;

    public ProgresoDTO getProgreso(Long id) {
        progresoValidacion.validateGetProgreso(id);

        Progreso progreso = progresoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un progreso con el ID proporcionado."));
        return Mapper.toDTOProgreso(progreso);
    }

    public List<ProgresoDTO> getAll() {
        List<Progreso> progresos = progresoRepository.findAll();
        List<ProgresoDTO> progresoDTOs = new ArrayList<>();

        progresos.forEach(progreso -> {
            ProgresoDTO progresoDTO = Mapper.toDTOProgreso(progreso);
            progresoDTOs.add(progresoDTO);
        });

        return progresoDTOs;
    }

    public ProgresoDTO addProgreso(ProgresoDTO progresoDTO) {
        progresoValidacion.validateAddProgreso(progresoDTO);

        Progreso progreso = new Progreso();
        progreso.setPeso(progresoDTO.getPeso());
        progreso.setRepiticiones(progresoDTO.getRepeticiones());
        progreso.setCalorias(progresoDTO.getCalorias());
        progreso.setObservaciones(progresoDTO.getObservaciones());

        if (progresoDTO.getId_sesion() != null) {
            Sesion_Entrenamiento sesion = sesionRepository.findById(progresoDTO.getId_sesion())
                    .orElseThrow(() -> new IllegalArgumentException("La sesión con ID " + progresoDTO.getId_sesion() + " no existe."));
            progreso.setId_sesion(sesion);
        } else {
            throw new IllegalArgumentException("El ID de la sesión no puede ser nulo.");
        }

        if (progresoDTO.getId_cliente() != null) {
            Usuario cliente = usuarioRepository.findById(progresoDTO.getId_cliente())
                    .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + progresoDTO.getId_cliente() + " no existe."));
            progreso.setId_cliente(cliente);
        } else {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo.");
        }

        Progreso savedProgreso = progresoRepository.save(progreso);

        return Mapper.toDTOProgreso(savedProgreso);
    }


    public ProgresoDTO updateProgreso(Long id, ProgresoDTO progresoDTO) {
        progresoValidacion.validateUpdateProgreso(id, progresoDTO);

        Progreso existingProgreso = progresoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Progreso no encontrado para el ID: " + id));

        if (progresoDTO.getPeso() != null) {
            existingProgreso.setPeso(progresoDTO.getPeso());
        }

        if (progresoDTO.getRepeticiones() > 0) {
            existingProgreso.setRepiticiones(progresoDTO.getRepeticiones());
        }

        if (progresoDTO.getCalorias() != null) {
            existingProgreso.setCalorias(progresoDTO.getCalorias());
        }

        if (progresoDTO.getObservaciones() != null) {
            existingProgreso.setObservaciones(progresoDTO.getObservaciones());
        }

        if (progresoDTO.getId_sesion() != null) {
            Sesion_Entrenamiento sesion = sesionRepository.findById(progresoDTO.getId_sesion())
                    .orElseThrow(() -> new IllegalArgumentException("La sesión con ID " + progresoDTO.getId_sesion() + " no existe."));
            existingProgreso.setId_sesion(sesion);
        }

        if (progresoDTO.getId_cliente() != null) {
            Usuario cliente = usuarioRepository.findById(progresoDTO.getId_cliente())
                    .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + progresoDTO.getId_cliente() + " no existe."));
            existingProgreso.setId_cliente(cliente);
        }

        Progreso updatedProgreso = progresoRepository.save(existingProgreso);

        return Mapper.toDTOProgreso(updatedProgreso);
    }


    public void deleteProgreso(Long id) {
        progresoValidacion.validateDeleteProgreso(id);

        progresoRepository.deleteById(id);
    }
}
