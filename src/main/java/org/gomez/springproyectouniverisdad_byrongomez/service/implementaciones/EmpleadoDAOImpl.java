package org.gomez.springproyectouniverisdad_byrongomez.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Empleado;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;
import org.gomez.springproyectouniverisdad_byrongomez.repository.EmpleadoRepository;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.EmpleadoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("empleadoRepository") EmpleadoRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findAll() {
        Iterable<Persona> personas = super.findAll();
        List<Persona> empleados = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Empleado) {
                empleados.add(persona);
            }
        }
        return empleados;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        Optional<Persona> optionalPersona=super.findById(id);
        Persona persona = optionalPersona.orElse(null);
        if (persona instanceof Empleado){
            return optionalPersona;
        }
        return null;
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository)repository).buscarEmpleadosPorTipoEmpleado(tipoEmpleado);
    }
}

