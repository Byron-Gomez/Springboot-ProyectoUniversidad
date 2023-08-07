package org.gomez.springproyectouniverisdad_byrongomez.controller.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.CarreraDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Carrera;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.CarreraMapperMs;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.CarreraDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
@Tag(name = "Carreras", description = "DTO controller de Carreras")
public class CarreraDTOController extends GenericDTOController<Carrera,CarreraDAO> {

    private final CarreraMapperMs carreraMapper;
    public CarreraDTOController(CarreraDAO service, CarreraMapperMs carreraMapper) {
        super(service, "Carrera");
        this.carreraMapper = carreraMapper;
    }

    @Operation(summary = "Obtiene todos las carreras")
    @GetMapping
    public ResponseEntity<?> findAllCarreras() {
        Map<String,Object> mensaje= new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) super.findAll();
        List<CarreraDTO> dtos = carreras
                .stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Obtiene carrera por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findCarreraById(@PathVariable Integer id) {
        Map<String,Object> mensaje= new HashMap<>();
        Optional<Carrera> optionalCarrera =  super.findId(id);
        Carrera carrera;
        CarreraDTO dto;
        if ( optionalCarrera== null || optionalCarrera.isEmpty()) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }else {
            carrera = optionalCarrera.get();
        }
        dto = carreraMapper.mapCarrera(carrera);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dto);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Crea una carrera")
    @PostMapping
    public ResponseEntity<?> createCarrera( @Valid @RequestBody CarreraDTO carreraDTO,BindingResult result ) {
        Map<String,Object> mensaje = new HashMap<>();

        Carrera carrera = carreraMapper.mapCarrera(carreraDTO);
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        super.createEntidad(carrera);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data","");
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @Operation(summary = "Modifica una carrera")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarrera(@PathVariable Integer id, @Valid @RequestBody CarreraDTO carreraDTO
            , BindingResult result){
        Map<String,Object> mensaje= new HashMap<>();
        Carrera carrera;
        CarreraDTO dto;
        Optional<Carrera> optionalCarrera = super.findId(id);

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }

        if (optionalCarrera ==null || optionalCarrera.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con di %d no existe",nombre_entidad,id));
            return ResponseEntity.badRequest().body(mensaje);
        }else {
            carrera = optionalCarrera.get();
        }
        dto = carreraMapper.mapCarrera(carrera);
        dto.setNombre(carreraDTO.getNombre());
        dto.setCantidad_anios(carreraDTO.getCantidad_anios());
        dto.setCantidad_materias(carreraDTO.getCantidad_materias());
        carrera = carreraMapper.mapCarrera(dto);

        mensaje.put("datos",service.save(carrera));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Borra una carrera ")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable Integer id){

        Map<String,Object> mensaje = new HashMap<>();
        Optional<Carrera> optionalCarrera = super.findId(id);

        if(optionalCarrera == null || optionalCarrera.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje",  String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        super.deleteById(id);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",  String.format("Se borro %s con Id %d", nombre_entidad, id));
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @Operation(summary = "Encuentra todos las carreras por nombre")
    @PostMapping("/find-carreras")
    public ResponseEntity<?> findCarreraByNombreContains(@RequestParam String carrera){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarreraByNombreContains(carrera);
        if (carreras.isEmpty()){
           mensaje.put("success",Boolean.TRUE);
           mensaje.put("mensaje", String.format("No existe %s : con nombre %s || El nombre de la carrera debe ser exacto ",nombre_entidad,carrera));
           return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
               .map(carreraMapper::mapCarrera)
               .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @Operation(summary = "Encuentra todos las carreras por nombre")
    @PostMapping("/find-carreras/ignorecase")
    public ResponseEntity<?> findCarreraByNombreContainsIgnoreCase(@RequestParam String carrera){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarreraByNombreContainsIgnoreCase(carrera);
        if (carreras.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existe %s : con nombre %s ",nombre_entidad,carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @Operation(summary = "Encuentra todos las carreras por cantidad de años")
    @PostMapping("/find-carreras/anios/{anios}")
    public ResponseEntity<?> findCarreraByCantidadAniosAfter(@PathVariable Integer anios){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarreraByCantidadAniosAfter(anios);
        if (carreras.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existe %s mayor a %d anios",nombre_entidad,anios));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @Operation(summary = "Encuentra todas las carreras por nombre y apellido del profesor")
    @GetMapping("profesor-carreras/{nombre}/{apellido}")
    public ResponseEntity<Map<String, Object>> findCarrerasProfesorNombreApellido(@PathVariable String nombre,
                                                                                  @PathVariable String apellido){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(nombre,apellido);
        if (carreras.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existe %s que sea impartida por el profesor: %s %s",nombre_entidad,nombre,apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }


}
