package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.ProfesorDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Profesor;

@MapperConfig
public interface ProfesorMapperConfig extends PersonaMapperConfig {
    @InheritConfiguration(name = "mapPersona")
    @InheritInverseConfiguration(name = "mapPersona")
    void mapProfesor(Profesor profesor, @MappingTarget ProfesorDTO profesorDTO);

}
