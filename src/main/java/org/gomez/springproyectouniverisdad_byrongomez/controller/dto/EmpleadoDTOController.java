package org.gomez.springproyectouniverisdad_byrongomez.controller.dto;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.EmpleadoDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PersonaDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Empleado;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.EmpleadoMapper;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.EmpleadoDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
@Tag(name = "Empleados", description = "DTO controller de empleados")
public class EmpleadoDTOController extends PersonaDTOController{

    public EmpleadoDTOController(@Qualifier("empleadoDAOImpl") PersonaDAO service, EmpleadoMapper empleadoMapper) {
        super(service, "empleado", empleadoMapper);
    }

    @Operation(summary = "Obtiene todos los empleados")
    @GetMapping
    public ResponseEntity<?> findAllEmpleados(){
        Map<String, Object> mensaje = new HashMap<>();
        List<PersonaDTO> dtos = super.findAllPersonas();
        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Obtiene el empleado por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findEmpleadoId(@PathVariable Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.findPersonaId(id);
        if (dto == null) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dto);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Crea un empeado")
    @PostMapping
    public ResponseEntity<?> createEmpleado(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        PersonaDTO save = super.createPersona(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",save);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @Operation(summary = "Modifica un empleado")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id,
                                            @Valid @RequestBody EmpleadoDTO empleadoDTO, BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO= super.findPersonaId(id);
        EmpleadoDTO dto;
        Empleado empleadoUpdate;

        if(personaDTO==null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        dto = ((EmpleadoDTO)personaDTO);
        dto.setNombre(empleadoDTO.getNombre());
        dto.setApellido(empleadoDTO.getApellido());
        dto.setDireccion(empleadoDTO.getDireccion());
        dto.setDni(empleadoDTO.getDni());
        dto.setTipoEmpleado(empleadoDTO.getTipoEmpleado());
        dto.setSueldo(empleadoDTO.getSueldo());
        dto.setPabellon(empleadoDTO.getPabellon());

        empleadoUpdate = empleadoMapper.mapEmpleado(dto);
        mensaje.put("datos",super.createPersona(empleadoUpdate));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Borra un empleado ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleadoId(@PathVariable Integer id){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO = super.findPersonaId(id);

        if(personaDTO==null) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje",  String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        super.deletePersonaId(id);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",  String.format("Se borro %s con Id %d", nombre_entidad, id));
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @Operation(summary = "Encuentra un empleado por nombre y apellido")
    @GetMapping("/nombre-apellido/{nombre}/{apellido}")
    public ResponseEntity<?> findEmpleadoNombreApellido(@PathVariable String nombre, @PathVariable String apellido){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO = super.findPersonaNombreApellido(
                nombre,apellido);
        if (personaDTO==null){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("No se encontro persona con nombre +%s y appelido %s",nombre,apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos",personaDTO);
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Encuentra un empleado por DNI")
    @GetMapping("/empleado-dni")
    public ResponseEntity<Map<String, Object>> findAlumnoDni(@RequestParam String dni){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.findPersonaDni(dni);

        if (dto == null){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro %s con DNI: %s",nombre_entidad,dni));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos",dto);
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Encuentra todos los empleados por tipo")
    @GetMapping("/tipo-empleado")
    public ResponseEntity<?> findEmpleadosTipoEmpleado(@RequestBody TipoEmpleado tipoEmpleado){

        Map<String,Object> mensaje = new HashMap<>();
        List<Persona> empleados = ((List<Persona>)((EmpleadoDAO)service).buscarEmpleadosPorTipoEmpleado(tipoEmpleado));
        if (empleados.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existen empleado de tipo: %s ",tipoEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<EmpleadoDTO> dtos = empleados.stream()
                .map(persona -> empleadoMapper.mapEmpleado((Empleado) persona))
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }
}
