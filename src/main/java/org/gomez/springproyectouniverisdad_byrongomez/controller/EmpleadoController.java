package org.gomez.springproyectouniverisdad_byrongomez.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.gomez.springproyectouniverisdad_byrongomez.exception.BadRequestException;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Empleado;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.EmpleadoDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;

import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "false")
public class EmpleadoController  extends PersonaController{


    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO service) {
        super(service);
        nombreEntidad = "Empleado";
    }

    @GetMapping("/tipo-empleado")
    public Iterable<Persona> buscarEmpleadosPorTipoEmpleado(@RequestBody TipoEmpleado tipoEmpleado){
        return ((EmpleadoDAO)service).buscarEmpleadosPorTipoEmpleado(tipoEmpleado);
    }

    @PutMapping("/{id}")
    public Persona actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado){
        Empleado empleadoUpdate;
        Optional<Persona> oEmpleado = service.findById(id);
        if(!oEmpleado.isPresent()) {
            throw new BadRequestException(String.format("Empleado con id %d no existe", id));
        }
        empleadoUpdate = (Empleado) oEmpleado.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido(empleado.getApellido());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        empleadoUpdate.setTipoEmpleado(empleado.getTipoEmpleado());
        empleadoUpdate.setSueldo(empleado.getSueldo());
        return service.save(empleadoUpdate);
    }
}