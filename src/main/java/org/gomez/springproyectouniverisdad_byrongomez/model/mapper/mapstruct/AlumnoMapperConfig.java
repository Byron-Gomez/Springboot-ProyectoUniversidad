package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.AlumnoDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
@MapperConfig
public interface AlumnoMapperConfig extends PersonaMapperConfig{
    @InheritConfiguration(name = "mapPersona")
    @InheritInverseConfiguration(name = "mapPersona")
    void mapAlumno(Alumno alumno, @MappingTarget AlumnoDTO alumnoDTO);

}
