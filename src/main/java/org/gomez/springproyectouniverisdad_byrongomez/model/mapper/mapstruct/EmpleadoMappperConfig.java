package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.EmpleadoDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Empleado;

@MapperConfig
public interface EmpleadoMappperConfig extends PersonaMapperConfig{

    @InheritConfiguration(name = "mapPersona")
    @InheritInverseConfiguration(name = "mapPersona")
    void mapEmpleado(Empleado empleado, @MappingTarget EmpleadoDTO empleadoDTO);

}
