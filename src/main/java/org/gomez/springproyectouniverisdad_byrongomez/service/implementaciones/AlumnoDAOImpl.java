package org.gomez.springproyectouniverisdad_byrongomez.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.repository.AlumnoRepository;
import org.gomez.springproyectouniverisdad_byrongomez.repository.PersonaRepository;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.AlumnoDAO;

import java.util.Optional;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {
    @Autowired
    public AlumnoDAOImpl(@Qualifier("alumnoRepository") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        Optional<Persona> optionalPersona=super.findById(id);
        Persona persona = optionalPersona.orElse(null);
        if (persona instanceof Alumno){
            return optionalPersona;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarAlumnosPorCarrera(String carrera) {
        return ((AlumnoRepository)repository).buscarAlumnosPorCarrera(carrera);
    }
}
