package org.gomez.springproyectouniverisdad_byrongomez.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AlumnoDTO extends PersonaDTO {

    private CarreraDTO carrera;

}
