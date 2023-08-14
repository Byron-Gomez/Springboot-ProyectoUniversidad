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
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PabellonDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.PabellonMapper;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.AulaDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PabellonDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pabellones")
@Tag(name = "Pabellones", description = "DTO controller de Pabellones")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")

public class PabellonDTOController extends GenericDTOController<Pabellon, PabellonDAO> {
    private final PabellonMapper pabellonMapper;
    private final AulaDAO aulaDAO;
    public PabellonDTOController(PabellonDAO service, PabellonMapper pabellonMapper, AulaDAO aulaDAO) {
        super(service, "pabellon");
        this.pabellonMapper = pabellonMapper;
        this.aulaDAO = aulaDAO;
    }

    @Operation(summary = "Obtener toda la lista de Pabellones")
    @GetMapping
    public ResponseEntity<?> findAllPabellones(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = super.findAll();
        List<PabellonDTO> dtos = pabellones.stream()
                .map(pabellonMapper::mapPabellon)
                .collect(Collectors.toList());

        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "buscar el Pabellon por su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findPabellonId(@PathVariable Integer id) {

        Map<String, Object> mensaje = new HashMap<>();
        Optional<Pabellon> optionalPabellon = super.findId(id);
        Pabellon pabellon;
        PabellonDTO dto;

        if (optionalPabellon == null || optionalPabellon.isEmpty()) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }else {
            pabellon = optionalPabellon.get();
        }
        dto = pabellonMapper.mapPabellon(pabellon);

        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dto);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Crear un Pabellon")
    @PostMapping
    public ResponseEntity<?> createPabellon(@Valid @RequestBody PabellonDTO pabellonDTO, BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }

        Pabellon pabellon = pabellonMapper.mapPabellon(pabellonDTO);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",super.createEntidad(pabellon));
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @Operation(summary = "Actualizar datos de un pabellon por su id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePabellon(@PathVariable Integer id,
                                            @Valid @RequestBody PabellonDTO pabellonDTO,BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();
        Optional<Pabellon> optionalPabellon = super.findId(id);
        Pabellon pabellon;
        PabellonDTO dto;

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }

        if(optionalPabellon == null || optionalPabellon.isEmpty()) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }else {
            pabellon = optionalPabellon.get();
        }
        dto = pabellonMapper.mapPabellon(pabellon);
        dto.setNombre(pabellonDTO.getNombre());
        dto.setMts2(pabellonDTO.getMts2());
        dto.setDireccion(pabellonDTO.getDireccion());
        pabellon = pabellonMapper.mapPabellon(dto);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",super.createEntidad(pabellon));
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Eliminar un Pabellon por su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePabellonId(@PathVariable Integer id){

        Map<String,Object> mensaje = new HashMap<>();
        Optional<Pabellon> optionalPabellon = super.findId(id);

        if(optionalPabellon == null || optionalPabellon.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje",  String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        super.deleteById(id);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",  String.format("Se borro %s con Id %d", nombre_entidad, id));
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @Operation(summary = "Buscar todos los Pabellones por su Localidad")
    @PostMapping("/pabellones-localidad")
    public ResponseEntity<?> findAllPabellonByLocalidad(@RequestParam String localidad){
        Map<String,Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.findAllPabellonByLocalidad(localidad);
        if (pabellones.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existen pabellones en localidad: %s ",localidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<PabellonDTO> dtos = pabellones.stream()
                .map(pabellonMapper::mapPabellon)
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Buscar todos los Pabellones por su Localidad")
    @PostMapping("/pabellones-nombre")
    public ResponseEntity<Map<String, Object>> findAllPabellonByNombre(@RequestParam String nombre){
       Map<String,Object> mensaje = new HashMap<>();
       List<Pabellon> pabellones = (List<Pabellon>) service.findAllPabellonByNombre(nombre);
        if (pabellones.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existen pabellones con el nombre: %s ",nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
       List<PabellonDTO> dtos = pabellones.stream()
                .map(pabellonMapper::mapPabellon)
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }
}
