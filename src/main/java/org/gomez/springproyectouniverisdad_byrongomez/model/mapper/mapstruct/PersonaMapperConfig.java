package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PersonaDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;

@MapperConfig
public interface PersonaMapperConfig {
    void mapPersona(Persona persona,@MappingTarget PersonaDTO PersonaDTO);
}
