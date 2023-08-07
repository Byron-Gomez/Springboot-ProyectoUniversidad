package org.gomez.springproyectouniverisdad_byrongomez.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gomez.springproyectouniverisdad_byrongomez.model.Direccion;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
@EqualsAndHashCode(callSuper = false)
public class Alumno extends Persona {
    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )

    @JoinColumn(name = "carreras_id")
    @JsonIgnoreProperties("carrera")
    private Carrera carrera;

    public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }


    @Override
    public String toString() {
        return super.toString()+" Alumno{}";
    }
}
