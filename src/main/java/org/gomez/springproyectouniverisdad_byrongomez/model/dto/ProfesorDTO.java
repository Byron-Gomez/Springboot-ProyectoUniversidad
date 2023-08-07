package org.gomez.springproyectouniverisdad_byrongomez.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProfesorDTO extends PersonaDTO {
    private BigDecimal sueldo;
    private Set<CarreraDTO>carreras;
}
