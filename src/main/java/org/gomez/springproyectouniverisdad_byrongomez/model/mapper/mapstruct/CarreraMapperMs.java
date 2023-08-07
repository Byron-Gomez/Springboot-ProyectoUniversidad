package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.CarreraDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Carrera;

@Mapper(componentModel = "spring")
public interface CarreraMapperMs {
    @Mappings({
            @Mapping(source = "id",target = "codigo"),
            @Mapping(source = "nombre",target = "nombre"),
            @Mapping(source = "cantidadMaterias",target = "cantidad_materias"),
            @Mapping(source = "cantidadAnios",target = "cantidad_anios"),
    })
    CarreraDTO mapCarrera(Carrera carrera);

    @Mappings({
            @Mapping(source = "codigo",target = "id"),
            @Mapping(source = "nombre",target = "nombre"),
            @Mapping(source = "cantidad_materias",target = "cantidadMaterias"),
            @Mapping(source = "cantidad_anios",target = "cantidadAnios"),
    })
    Carrera mapCarrera(CarreraDTO carreraDTO);
}
