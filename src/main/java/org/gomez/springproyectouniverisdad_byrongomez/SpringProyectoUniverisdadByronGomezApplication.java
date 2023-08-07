package org.gomez.springproyectouniverisdad_byrongomez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProyectoUniverisdadByronGomezApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProyectoUniverisdadByronGomezApplication.class, args).getBeanDefinitionNames();
    }
}


