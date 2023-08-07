package org.gomez.springproyectouniverisdad_byrongomez.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;

@Repository("empleadoRepository")
public interface EmpleadoRepository extends PersonaRepository{
    @Query("SELECT e FROM Empleado e  WHERE e.tipoEmpleado=?1")
    Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado);
}
