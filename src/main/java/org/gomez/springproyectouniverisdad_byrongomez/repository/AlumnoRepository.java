package org.gomez.springproyectouniverisdad_byrongomez.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;

@Repository("alumnoRepository")
public interface AlumnoRepository extends PersonaRepository {
    @Query("SELECT a FROM Alumno a JOIN a.carrera c WHERE c.nombre=?1")
    Iterable<Persona> buscarAlumnosPorCarrera(String nombre);
}
