package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.AlumnoDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;

@Mapper(componentModel = "spring",config = AlumnoMapperConfig.class,uses = CarreraMapperMs.class)
public interface AlumnoMapper {

     AlumnoDTO mapAlumno(Alumno alumno);
     Alumno mapAlumno(AlumnoDTO alumnoDTO);

}
