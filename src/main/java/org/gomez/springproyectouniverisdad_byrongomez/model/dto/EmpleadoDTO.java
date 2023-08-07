package org.gomez.springproyectouniverisdad_byrongomez.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmpleadoDTO extends PersonaDTO{
    private BigDecimal sueldo;
    private TipoEmpleado tipoEmpleado;
    private Pabellon pabellon;

}
