package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Aula;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.Pizarron;

import java.util.Optional;

public interface AulaDAO extends GenericoDAO<Aula> {
    Iterable<Aula>findAulasByPizarron(Pizarron pizarron);

    Iterable<Aula>findAulasByPabellonNombre(String nombre);

    Optional<Aula> findAulaByNroAula(Integer nroAula);
}
