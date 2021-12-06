package ne.directaid.mapsapi.controllers;

import lombok.AllArgsConstructor;
import ne.directaid.mapsapi.dto.ProjetDTO;
import ne.directaid.mapsapi.exceptions.RequestException;
import ne.directaid.mapsapi.models.Projet;
import ne.directaid.mapsapi.services.ProjetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/projets")
@AllArgsConstructor
@Transactional
public class ProjetController
{
    final ProjetService projetService;

    @GetMapping(value="/")
    public ResponseEntity<Iterable<ProjetDTO>> getProjets() {
        return ResponseEntity.ok(projetService.getProjets());
    }

    @GetMapping(value="/all")
    public ResponseEntity<Iterable<Projet>> getAllProjets() {
        return ResponseEntity.ok(projetService.getAllProjets());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<Projet>> getProjet(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projetService.getProjet(id));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProjet(@PathVariable("id") Long id) {
        return projetService.deleteProjet(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<Object> saveProjet(@Valid @RequestBody Projet projet) {
        ResponseEntity<Object> response;
        try
        {
            if(Boolean.TRUE.equals(projetService.projetExists(projet)))
                throw new RequestException("Ce numéro de projet existe déjà !");
            else response = ResponseEntity.ok(projetService.addProjet(projet));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return response;
    }
}
