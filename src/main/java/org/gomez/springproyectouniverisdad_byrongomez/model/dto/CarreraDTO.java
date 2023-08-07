package org.gomez.springproyectouniverisdad_byrongomez.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {

    private Integer codigo;
    @NotNull
    @NotEmpty
    @Size(max = 80)
    @Column(nullable = false, unique = true, length = 80)
    private String nombre;
    @Min(value = 0)
    @Positive(message = "El valor no puede ser negativo")
    private Integer cantidad_materias;
    @Min(value = 0)
    @Positive
    private Integer cantidad_anios;

}
