package org.gomez.springproyectouniverisdad_byrongomez.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PersonaDAO;

@Component
public class InitCommand {
    @Autowired
    @Qualifier(value = "alumnoDAOImpl")
    private PersonaDAO servicioAlumno;

//    @Autowired
//    @Qualifier(value = "empleadoDAOImpl")
//    private PersonaDAO servicioEmpleado;
//    @Autowired
//    @Qualifier(value = "profesorDAOImpl")
//    private PersonaDAO servicioProfesor;
//    @Autowired
//    private AulaDAO servicioAula;
//    @Autowired
//    private PabellonDAO servicioPabellon;
//    @Autowired
//    private CarreraDAO servicioCarrera;
}
