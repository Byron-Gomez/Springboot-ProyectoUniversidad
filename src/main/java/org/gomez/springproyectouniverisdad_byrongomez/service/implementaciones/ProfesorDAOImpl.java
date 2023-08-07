package org.gomez.springproyectouniverisdad_byrongomez.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Profesor;
import org.gomez.springproyectouniverisdad_byrongomez.repository.PersonaRepository;
import org.gomez.springproyectouniverisdad_byrongomez.repository.ProfesorRepository;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.ProfesorDAO;

import java.util.Optional;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
    @Autowired
    public ProfesorDAOImpl(@Qualifier("profesorRepository") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        Optional<Persona> optionalPersona=super.findById(id);
        Persona persona = optionalPersona.orElse(null);
        if (persona instanceof Profesor){
            return optionalPersona;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarProfesoresPorCarrera(String carrera) {
         return  ((ProfesorRepository)repository).buscarProfesoresPorCarrera(carrera);
    }
}
