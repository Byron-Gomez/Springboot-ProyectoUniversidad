package org.gomez.springproyectouniverisdad_byrongomez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Carrera;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.AlumnoDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.CarreraDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "false")
@Deprecated
public class AlumnoController extends PersonaController{
    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDao, CarreraDAO carreraDAO) {
        super(alumnoDao);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id,
                                              @RequestBody Alumno alumno){
        Map<String,Object> mensaje = new HashMap<>();
        Alumno alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if(!oAlumno.isPresent()) {
//            throw new BadRequestException(String.format("Alumno con id %d no existe", id));
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Alumno con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        alumnoUpdate = (Alumno) oAlumno.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());
        mensaje.put("datos",service.save(alumnoUpdate));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @GetMapping("/alumnos-carrera/{carrera}")
    public Iterable<Persona> buscarAlumnosPorcarrera(@PathVariable String carrera){
        return ((AlumnoDAO)service).buscarAlumnosPorCarrera(carrera);
    }


    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno, @PathVariable Integer idCarrera){
        Map<String,Object> mensaje= new HashMap<>();
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if(!oAlumno.isPresent()) {
//            throw new BadRequestException(String.format("Alumno con id %d no existe", idAlumno));
        mensaje.put("success",Boolean.FALSE);
        mensaje.put("mensaje",String.format("Alumno con id %d no existe", idAlumno));
        return ResponseEntity.badRequest().body(mensaje);
        }

        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if(!oCarrera.isPresent()){
//            throw new BadRequestException(String.format("Carrera con id %d no existe", idCarrera));
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Carrera con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();

        ((Alumno)alumno).setCarrera(carrera);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("datos",service.save(alumno));
        return ResponseEntity.ok().body(mensaje);
    }
}
