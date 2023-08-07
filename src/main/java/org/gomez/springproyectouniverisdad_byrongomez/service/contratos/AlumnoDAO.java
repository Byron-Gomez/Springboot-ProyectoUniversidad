package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;

public interface AlumnoDAO extends PersonaDAO {
//    Iterable<Aula>findAulasByPizarron(Pizarron pizarron);
//    Iterable<Aula>findAulasByPabellonNombre(String nombre);
//    Optional<Aula> findAulaByNroAula(Integer nroAula);
Iterable<Persona> buscarAlumnosPorCarrera(String carrera);
}
