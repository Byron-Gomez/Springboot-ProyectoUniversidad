package org.gomez.springproyectouniverisdad_byrongomez.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.gomez.springproyectouniverisdad_byrongomez.exception.BadRequestException;
import org.gomez.springproyectouniverisdad_byrongomez.model.entity.*;
import org.gomez.springproyectouniverisdad_byrongomez.model.enums.Pizarron;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.AulaDAO;
import org.gomez.springproyectouniverisdad_byrongomez.service.contratos.PabellonDAO;

import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "false")
public class AulaController extends GenericController<Aula, AulaDAO>{
    private final PabellonDAO pabellonDAO;
      public AulaController(AulaDAO service, PabellonDAO pabellonDAO) {
        super(service);
          this.pabellonDAO = pabellonDAO;
      }

    @PostMapping("/aulas-pizarras")
    public  Iterable<Aula>findAulasByPizarron(@RequestBody Pizarron pizarron){
          return service.findAulasByPizarron(pizarron);
    }
    @PostMapping("/aulas-pabellon")
    public Iterable<Aula>findAulasByPabellonNombre(@RequestBody String nombre){
          return service.findAulasByPabellonNombre(nombre);
    }
    @GetMapping("/nroaulas/{nroAula}")
    public Optional<Aula> findAulaByNroAula(@PathVariable Integer nroAula){
          return service.findAulaByNroAula(nroAula);
    }

    @PutMapping("/{idAula}/pabellon/{idPabellon}")
    public Aula asignarPabellonAula(@PathVariable Integer idAula, @PathVariable Integer idPabellon){
        Optional<Aula> oAula = service.findById(idAula);
        if(!oAula.isPresent()) {
            throw new BadRequestException(String.format("Aula con id %d no existe", idAula));
        }
        Optional<Pabellon> oPabellon = pabellonDAO.findById(idPabellon);
        if(!oPabellon.isPresent()){
            throw new BadRequestException(String.format("Pabellon con id %d no existe", idPabellon));
        }

        Pabellon pabellon = oPabellon.get();
        Aula aula = oAula.get();
        aula.setPabellon(pabellon);
        return service.save(aula);
    }

    @PutMapping("/{id}")
    public Aula actualizarAula(@PathVariable Integer id, @RequestBody Aula aula){
        Aula aulaUpdate = null;
        Optional<Aula> oAula = service.findById(id);
        if(!oAula.isPresent()) {
            throw new BadRequestException(String.format("Aula con id %d no existe", id));
        }
        aulaUpdate =  oAula.get();
        aulaUpdate.setNroAula(aula.getNroAula());
        aulaUpdate.setMedidas(aula.getMedidas());
        aulaUpdate.setPizarron(aula.getPizarron());
        aulaUpdate.setCantPupitres(aula.getCantPupitres());

        return service.save(aulaUpdate);
    }
}
