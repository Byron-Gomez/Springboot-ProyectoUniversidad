package org.gomez.springproyectouniverisdad_byrongomez.service.contratos;

import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO {
    Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado);
}
