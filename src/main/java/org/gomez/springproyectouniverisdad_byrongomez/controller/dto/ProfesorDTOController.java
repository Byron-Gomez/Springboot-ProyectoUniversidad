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
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PersonaDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.ProfesorDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.*;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.CarreraMapperMs;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.ProfesorMapper;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.CarreraDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.ProfesorDAO;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
@Tag(name = "Profesores", description = "DTO controller de profesores")
public class ProfesorDTOController extends PersonaDTOController{

    private final CarreraDAO carreraDAO;
    private final CarreraMapperMs carreraMapperMs;

    public ProfesorDTOController(@Qualifier("profesorDAOImpl") PersonaDAO service, ProfesorMapper profesorMapper, CarreraDAO carreraDAO, CarreraMapperMs carreraMapperMs) {
        super(service, "profesor", profesorMapper);
        this.carreraDAO = carreraDAO;
        this.carreraMapperMs = carreraMapperMs;
    }

    @Operation(summary = "Obtiene todos los profesores")
    @GetMapping
    public ResponseEntity<?> findAllProfesores(){
        Map<String, Object> mensaje = new HashMap<>();
        List<PersonaDTO> dtos = super.findAllPersonas();
        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Obtiene el profesor por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findProfesorId(@PathVariable Integer id) {
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

    @Operation(summary = "Crea un profesor")
    @PostMapping
    public ResponseEntity<?> createProfesor(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        PersonaDTO save = super.createPersona(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",save);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @Operation(summary = "Modifica un profesor")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfesor(@PathVariable Integer id,
                                            @Valid @RequestBody ProfesorDTO profesorDTO, BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO= super.findPersonaId(id);
        ProfesorDTO dto;
        Profesor profesorUpdate;

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
        dto = ((ProfesorDTO)personaDTO);
        dto.setNombre(profesorDTO.getNombre());
        dto.setApellido(profesorDTO.getApellido());
        dto.setDireccion(profesorDTO.getDireccion());
        dto.setDni(profesorDTO.getDni());
        dto.setSueldo(profesorDTO.getSueldo());

        profesorUpdate = profesorMapper.mapProfesor(dto);
        mensaje.put("datos",super.createPersona(profesorUpdate));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Borra un profesor por id ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesorId(@PathVariable Integer id){
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

    @Operation(summary = "Encuentra un profesor por nombre y apellido")
    @GetMapping("/nombre-apellido/{nombre}/{apellido}")
    public ResponseEntity<?> findProfesorNombreApellido(
            @PathVariable String nombre, @PathVariable String apellido){
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

    @Operation(summary = "Encuentra un profesor por DNI")
    @GetMapping("/profesor-dni")
    public ResponseEntity<Map<String, Object>> findProfesorDni(@RequestParam String dni){
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

    @Operation(summary = "Encontrar todos los profesores de una carrera")
    @GetMapping("/profesores-carreras")
    public ResponseEntity<?> findProfesoresCarrera(@RequestBody String carrera){

        Map<String,Object> mensaje = new HashMap<>();
        List<Persona> profeores = ((List<Persona>)((ProfesorDAO)service).buscarProfesoresPorCarrera(carrera));
        if (profeores.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existen profesores en carrera: %s ",carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<ProfesorDTO> dtos = profeores.stream()
                .map(persona -> profesorMapper.mapProfesor((Profesor) persona))
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Asigna una carrera a profesor")
    @PutMapping("/{idProfesor}/carrera/{idCarrera}")
    public ResponseEntity<?> assignCarreraProfesor(@PathVariable Integer idProfesor, @PathVariable Integer idCarrera){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO = super.findPersonaId(idProfesor);
        Optional<Carrera> oCarrera;
        Profesor profesor;
        Carrera carrera;
        Set<Carrera> carreras;

        if(personaDTO==null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }

        oCarrera = carreraDAO.findById(idCarrera);
        if(oCarrera.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Carrera con id %d no existe",idCarrera ));
            return ResponseEntity.badRequest().body(mensaje);
        }
        profesor = profesorMapper.mapProfesor((ProfesorDTO) personaDTO);
        carrera = oCarrera.get();
        carreras = new HashSet<>();
        carreras.add(carrera);
        profesor.setCarreras(carreras);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",service.save(profesor));
        return ResponseEntity.ok().body(mensaje);
    }
}

