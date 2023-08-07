package org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.EmpleadoDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Empleado;

@Mapper(componentModel = "spring",config = EmpleadoMappperConfig.class)
public  interface EmpleadoMapper {

    EmpleadoDTO mapEmpleado(Empleado empleado);
    Empleado mapEmpleado(EmpleadoDTO empleadoDTO);

}
