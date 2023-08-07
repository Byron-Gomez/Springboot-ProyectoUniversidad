package org.gomez.springproyectouniverisdad_byrongomez.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;
import org.gomez.springproyectouniverisdad_byrongomez.repository.PabellonRepository;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PabellonDAO;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {
    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Pabellon> findAllPabellonByLocalidad(String localidad) {
        return repository.findAllPabellonByLocalidad(localidad);
    }

    @Override
    public Iterable<Pabellon> findAllPabellonByNombre(String nombre) {
        return repository.findAllPabellonByNombre(nombre);
    }

}
