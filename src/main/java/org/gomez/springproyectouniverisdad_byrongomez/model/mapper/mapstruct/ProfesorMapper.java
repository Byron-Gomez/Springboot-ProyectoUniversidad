package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.ProfesorDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Profesor;

@Mapper(componentModel = "spring",config = ProfesorMapperConfig.class)
public interface ProfesorMapper {

    ProfesorDTO mapProfesor(Profesor profesor);
    Profesor mapProfesor(ProfesorDTO profesorDTO);
}
