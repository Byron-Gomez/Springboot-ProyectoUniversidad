package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera> {

    Iterable<Carrera> findCarreraByNombreContains(String nombre);
    Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios);
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre,
                                                               String apellido);
}
