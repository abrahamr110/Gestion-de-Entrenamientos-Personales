package com.es.Gestion.de.Entrenamientos.Personales.util;

import com.es.Gestion.de.Entrenamientos.Personales.DTO.UsuarioDTO;
import com.es.Gestion.de.Entrenamientos.Personales.Entities.Usuario;

public class Mapper {
    private Mapper() {}

    public static UsuarioDTO toDTO(Usuario user) {
        return new UsuarioDTO(user.getNombre(), user.getCorreo(), user.getContrasena(), user.getRol(), user.getFecha_creacion());
    }
}