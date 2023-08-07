package org.gomez.springproyectouniverisdad_byrongomez.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.Pizarron;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO {

    private Integer id;
    @NotNull
    private Integer nroAula;
    private String medidas;
    private Integer cantPupitres;
    private Pizarron pizarron;
    private PabellonDTO pabellon;

}
