package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;

import java.util.Optional;

public interface PersonaDAO extends GenericoDAO<Persona> {
    Optional<Persona> findNameLastName(String nombre,String apellido);
    Optional<Persona> findDni(String dni);
    Iterable<Persona> findLastName(String apellido);
}
