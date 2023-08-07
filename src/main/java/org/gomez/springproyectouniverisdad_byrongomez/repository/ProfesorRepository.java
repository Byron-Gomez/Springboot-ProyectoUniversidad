package org.gomez.springproyectouniverisdad_byrongomez.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;

@Repository
public interface ProfesorRepository extends PersonaRepository{
    @Query("SELECT p FROM Profesor p JOIN p.carreras c WHERE c.nombre =?1")
    Iterable<Persona> buscarProfesoresPorCarrera(String carrera);
}
