package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon> {
    Iterable<Pabellon> findAllPabellonByLocalidad(String localidad);
    Iterable<Pabellon> findAllPabellonByNombre(String nombre);
}
