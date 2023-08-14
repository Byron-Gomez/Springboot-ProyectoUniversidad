package org.gomez.springproyectouniverisdad_byrongomez.controller.dto;

import io.swagger.v3.oas.annotations.Operation;
import org.gomez.springproyectouniverisdad_byrongomez.model.dto.PersonaDTO;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Alumno;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Empleado;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Persona;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Profesor;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.AlumnoMapper;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.EmpleadoMapper;
import org.gomez.springproyectouniverisdad_byrongomez.model.mapper.mapstruct.ProfesorMapper;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PersonaDTOController extends GenericDTOController<Persona, PersonaDAO>{

    protected AlumnoMapper alumnoMapper;
    protected EmpleadoMapper empleadoMapper;
    protected ProfesorMapper profesorMapper;

    public PersonaDTOController(PersonaDAO service, String nombre_entidad, AlumnoMapper alumnoMapper) {
        super(service, nombre_entidad);
        this.alumnoMapper = alumnoMapper;
    }
    public PersonaDTOController(PersonaDAO service, String nombre_entidad, EmpleadoMapper empleadoMapper) {
        super(service, nombre_entidad);
        this.empleadoMapper = empleadoMapper;
    }

    public PersonaDTOController(PersonaDAO service, String nombre_entidad, ProfesorMapper profesorMapper) {
        super(service, nombre_entidad);
        this.profesorMapper = profesorMapper;
    }

    @Operation(summary = "Obtener toda la lista de Personas")
    public List<PersonaDTO> findAllPersonas(){

        List<Persona> personas = super.findAll();
        List<PersonaDTO> dtos = new ArrayList<>();

        personas.forEach(persona -> {
            if (persona instanceof Alumno && alumnoMapper != null) {
                dtos.add(alumnoMapper.mapAlumno((Alumno) persona));
            } else if (persona instanceof Profesor && profesorMapper != null) {
                dtos.add(profesorMapper.mapProfesor((Profesor) persona));
            } else if (persona instanceof Empleado && empleadoMapper != null) {
                dtos.add(empleadoMapper.mapEmpleado((Empleado) persona));
            }
        });

        return dtos;
    }

    @Operation(summary = "Obtener una Persona por su id")
    public PersonaDTO findPersonaId(Integer id){

        Optional<Persona>optionalPersona = super.findId(id);
        Persona persona;
        PersonaDTO dto = null;

        if (optionalPersona == null || optionalPersona.isEmpty()) {
                return null;
        }else {
                persona = optionalPersona.get();
        }

        if (persona instanceof Alumno){
                dto = alumnoMapper.mapAlumno((Alumno) persona);
        }else if(persona instanceof Profesor){
                dto = profesorMapper.mapProfesor((Profesor) persona);
        }else  if (persona instanceof Empleado){
                dto = empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
    }

    @Operation(summary = "Crear un Pabellon")
    public PersonaDTO createPersona(Persona persona){

        Persona personaEntidad = super.createEntidad(persona);
        PersonaDTO dto = null;

        if (personaEntidad instanceof Alumno){
            dto =alumnoMapper.mapAlumno((Alumno) personaEntidad);
        }else if(personaEntidad instanceof Profesor){
            dto =profesorMapper.mapProfesor((Profesor) personaEntidad);

        }else  if (personaEntidad instanceof Empleado){
            dto =empleadoMapper.mapEmpleado((Empleado) personaEntidad);
        }

        return dto;
    }

    @Operation(summary = "Eliminar una Persona por su id")
    public void deletePersonaId(Integer id){
        super.deleteById(id);
    }

    @Operation(summary = "Burcar una Persona por su DNI")
    public PersonaDTO findPersonaNombreApellido( String nombre,String apellido){

        Optional<Persona>optionalPersona =service.findNameLastName(nombre,apellido);
        Persona persona;
        PersonaDTO dto = null;

        if (optionalPersona == null) {
            return null;
        }else {
            persona=optionalPersona.get();
        }

        if (persona instanceof Alumno){
            dto =alumnoMapper.mapAlumno((Alumno) persona);

        }else if(persona instanceof Profesor){
            dto =profesorMapper.mapProfesor((Profesor) persona);

        }else  if (persona instanceof Empleado){
            dto =empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
    }

    @Operation(summary = "Buscar una Persona por DNI")
    public PersonaDTO findPersonaDni(String dni){

        Optional<Persona>optionalPersona =service.findDni(dni);
        Persona persona;
        PersonaDTO dto = null;

        if (optionalPersona == null || optionalPersona.isEmpty()) {
            return null;
        }else {
           persona= optionalPersona.get();
        }
        if (persona instanceof Alumno){
            dto = alumnoMapper.mapAlumno((Alumno) persona);

        }else if(persona instanceof Profesor){
            dto =profesorMapper.mapProfesor((Profesor) persona);

        }else  if (persona instanceof Empleado){
            dto =empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
    }

}
