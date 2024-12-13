package com.es.Gestion.de.Entrenamientos.Personales.DTO;

import java.util.Date;

public class Plan_EntrenamientoDTO {
    private String nombre;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_final;

    public Plan_EntrenamientoDTO(String nombre, String descripcion, Date fecha_inicio, Date fecha_final) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }
}