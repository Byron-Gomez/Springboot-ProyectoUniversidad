package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.AulaDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PabellonDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Aula;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;

@Mapper(componentModel = "spring")
public interface PabellonMapper {
    PabellonDTO mapPabellon(Pabellon pabellon);
    Pabellon mapPabellon(PabellonDTO pabellon);
    @Mapping(target = "pabellon", ignore = true)
    @Mapping(target = "medidas", ignore = true)
    @Mapping(target = "pizarron", ignore = true)
    @Mapping(target = "cantPupitres", ignore = true)
    AulaDTO mapAula(Aula aula);

}
