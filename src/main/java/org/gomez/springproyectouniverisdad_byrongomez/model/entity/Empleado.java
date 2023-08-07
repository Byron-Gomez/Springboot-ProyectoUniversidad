package org.gomez.springproyectouniverisdad_byrongomez.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gomez.springproyectouniverisdad_byrongomez.model.Direccion;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
@EqualsAndHashCode(callSuper = false)
public class Empleado extends Persona {

    private BigDecimal sueldo;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_empleado")
    private TipoEmpleado tipoEmpleado;
    @OneToOne(
            optional = true,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "pabellon_id",
            foreignKey = @ForeignKey(name = "FK_PABELLON_ID")
    )
    private Pabellon pabellon;

    public Empleado(Integer id, String nombre, String apellido, String dni,  Direccion direccion, BigDecimal sueldo, TipoEmpleado tipoEmpleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return "\tEmpleado:" + super.toString()+
                " sueldo: " + sueldo +
                ", tipoEmpleado: " + tipoEmpleado +
                ", pabellon: " + pabellon;
    }
}
