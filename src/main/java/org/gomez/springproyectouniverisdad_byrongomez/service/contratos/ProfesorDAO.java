package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;

public interface ProfesorDAO extends PersonaDAO {
    Iterable<Persona> buscarProfesoresPorCarrera(String carrera);
}
