package org.gomez.springproyectouniverisdad_byrongomez.service.implementaciones;

import org.springframework.transaction.annotation.Transactional;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.repository.PersonaRepository;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;

import java.util.Optional;

public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO {

    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findNameLastName(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre,apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findDni(String dni) {
        return repository.buscarPorDni(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findLastName(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }
}
