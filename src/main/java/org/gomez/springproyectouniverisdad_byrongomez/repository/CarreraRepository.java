package org.gomez.springproyectouniverisdad_byrongomez.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera,Integer> {
    Iterable<Carrera> findCarreraByNombreContains(String nombre);
    Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios);
    @Query("SELECT c FROM Carrera c JOIN FETCH c.profesores p" +
            " WHERE p.nombre=?1 AND p.apellido=?2")
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre,
                                                               String apellido);
}
