package org.gomez.springproyectouniverisdad_byrongomez.command;

import org.gomez.springproyectouniverisdad_byrongomez.model.Direccion;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.*;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.Pizarron;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.TipoEmpleado;

import java.math.BigDecimal;

public class ObjetosDummy {
    public static Aula getAula123(){
        return new Aula(null, 123, "24 x 36", 50, Pizarron.PIZARRA_ACRILICO);
    }

    public static Aula getAula231(){
        return new Aula(null, 231, "20 x 30", 50, Pizarron.PIZARRA_TIZA);
    }

    public static Aula getAula451(){
        return new Aula(null, 451, "15 x 25", 50, Pizarron.PIZARRA_ACRILICO);
    }

    public static Aula getAula789(){
        return new Aula(null, 789, "30 x 40", 50, Pizarron.PIZARRA_TIZA);
    }

    public static Aula getAula567(){
        return new Aula(null, 567, "18 x 24", 50, Pizarron.PIZARRA_ACRILICO);
    }

    public static Pabellon getPabellonUno(){
        return new Pabellon(null, "Pabellon Uno",450.5, new Direccion("Calle Uno", "1453", "1623", "", "", "Rodriguez Peña"));
    }

    public static Pabellon getPabellonDos(){
        return new Pabellon(null,"Pabellon Dos", 750.0,  new Direccion("Calle Dos", "584", "1623", "", "", "Rodriguez Peña"));
    }
    public static Pabellon getPabellonTres(){
        return new Pabellon(null, "Pabellon Tres", 600.75, new Direccion("Calle Tres", "732", "1623", "", "", "Rodriguez Peña"));
    }

    public static Pabellon getPabellonCuatro(){
        return new Pabellon(null, "Pabellon Cuatro", 900.25, new Direccion("Calle Cuatro", "921", "1623", "", "", "Rodriguez Peña"));
    }

    public static Pabellon getPabellonCinco(){
        return new Pabellon(null, "Pabellon Cinco", 1200.5, new Direccion("Calle Cinco", "1250", "1623", "", "", "Rodriguez Peña"));
    }


    public static Carrera getCarreraIngSis(){
        return new Carrera(null, "Ingenieria en Sistemas", 60, 5);
    }

    public static Carrera getCarreraLicTur(){
        return new Carrera(null, "Licenciatura en Turismo", 42, 4);
    }

    public static Persona getAlumnoUno(){
        return new Alumno(null, "Lautaro", "Gimenez", "34562189", new Direccion());
    }

    public static Persona getAlumnoDos(){
        return new Alumno(null, "Laura", "Videla", "32159753", new Direccion());
    }

    public static Persona getAlumnoTres(){
        return new Alumno(null, "Martín", "López", "26783145", new Direccion());
    }

    public static Persona getAlumnoCuatro(){
        return new Alumno(null, "Ana", "Martínez", "39821476", new Direccion());
    }

    public static Persona getAlumnoCinco(){
        return new Alumno(null, "Diego", "Pérez", "18652439", new Direccion());
    }

    public static Persona getProfesorUno(){
        return new Profesor(null, "Horacio", "Laurin", "22695142", new Direccion(), new BigDecimal("25600"));
    }

    public static Persona getProfesorDos(){
        return new Profesor(null, "Ruben", "Gonzalez", "25943608", new Direccion(), new BigDecimal("23500"));
    }
    public static Persona getProfesorTres(){
        return new Profesor(null, "María", "Rodríguez", "31870952", new Direccion(), new BigDecimal("28000"));
    }

    public static Persona getProfesorCuatro(){
        return new Profesor(null, "Andrés", "López", "28190367", new Direccion(), new BigDecimal("24500"));
    }

    public static Persona getProfesorCinco(){
        return new Profesor(null, "Carolina", "Mendoza", "37581429", new Direccion(), new BigDecimal("26500"));
    }


    public static Persona getEmpleadoUno(){
        return new Empleado(null, "Lucrecia","Dominguez", "30942108", new Direccion(), new BigDecimal("15600"), TipoEmpleado.ADMINISTRATIVO);
    }
    public static Persona getEmpleadoDos(){
        return new Empleado(null, "Roberto", "Lugones", "28902674", new Direccion(), new BigDecimal("14250"), TipoEmpleado.MANTENIMIENTO);
    }
    public static Persona getEmpleadoTres(){
        return new Empleado(null, "Valeria", "Fernández", "27683159", new Direccion(), new BigDecimal("16500"), TipoEmpleado.MANTENIMIENTO);
    }

    public static Persona getEmpleadoCuatro(){
        return new Empleado(null, "Javier", "Mendoza", "35698241", new Direccion(), new BigDecimal("13500"), TipoEmpleado.ADMINISTRATIVO);
    }

    public static Persona getEmpleadoCinco(){
        return new Empleado(null, "Carla", "Gutiérrez", "39821756", new Direccion(), new BigDecimal("14800"), TipoEmpleado.MANTENIMIENTO);
    }


}
