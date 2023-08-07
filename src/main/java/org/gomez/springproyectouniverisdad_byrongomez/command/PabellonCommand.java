package org.gomez.springproyectouniverisdad_byrongomez.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Aula;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.Pabellon;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.AulaDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PabellonDAO;

@Component
@Order(3)
public class PabellonCommand implements CommandLineRunner {
    @Autowired
    PabellonDAO pabellonDAO;
    @Autowired
    AulaDAO aulaDAO;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------- ************* Alumnos Command ********** --------");
        pabellonDAO.save(ObjetosDummy.getPabellonUno());
        pabellonDAO.save(ObjetosDummy.getPabellonDos());

        System.out.println("----Pabellones---");
        Iterable<Pabellon> pabellones= pabellonDAO.findAll();
        pabellones.forEach(System.out::println);

        System.out.println("----Pabellones por localidad---");
        pabellones=pabellonDAO.findAllPabellonByLocalidad("Rodriguez Peña");
        pabellones.forEach(System.out::println);

        System.out.println("----Pabellones por nombre---");
        pabellonDAO.findAllPabellonByNombre("Pabellon Dos");

        System.out.println("----Aulas por pabellon nombre---");
        Pabellon pabellon=pabellonDAO.findById(1).orElseThrow();
        Iterable<Aula>aulas= aulaDAO.findAulasByPabellonNombre(pabellon.getNombre());
        aulas.forEach(System.out::println);



    }
}
