package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.AulaDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PabellonDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Aula;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;


@Mapper(componentModel = "spring")
public interface AulaMapper {
    AulaDTO mapAula(Aula aula);
    Aula mapAula(AulaDTO aula);
    @Mapping(target = "mts2", ignore = true)
    @Mapping(target = "aulas", ignore = true)
    PabellonDTO pabellonToPabellonDTO(Pabellon pabellon);
    
}
