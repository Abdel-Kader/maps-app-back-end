package ne.directaid.mapsapi.services;

import lombok.AllArgsConstructor;
import ne.directaid.mapsapi.dto.ProjetDTO;
import ne.directaid.mapsapi.models.Projet;
import ne.directaid.mapsapi.repositories.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjetService
{
    private final ProjetRepository projetRepository;

    public Iterable<ProjetDTO> getProjets() {
        List<Projet> projets = projetRepository.findAll();
        List<ProjetDTO> results = new ArrayList<>();
        for (int i = 0; i < projets.toArray().length; i++)
        {
            System.out.println(i);
            System.out.println(projets.get(i).getLatProjet());
            ProjetDTO result = new ProjetDTO();
            HashMap<String, Double> coordinate = new HashMap<>();
            result.setNom(projets.get(i).getNomProjet());
            result.setNumero(projets.get(i).getNumeroProjet());
            result.setType(projets.get(i).getTypeProjet());
            result.setImage(projets.get(i).getPhotoProjet());
            result.setDateExec(projets.get(i).getDateExecution());
            coordinate.put("latitude", Double.parseDouble(projets.get(i).getLatProjet()));
            coordinate.put("longitude", Double.parseDouble(projets.get(i).getLngProjet()));
            result.setCoordinate(coordinate);
            results.add(result);
        }
        return results;
    }

    public Iterable<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Optional<Projet> getProjet(Long id) {
        return projetRepository.findById(id);
    }

    public Boolean projetExists(Projet projetToVerify) {
        Projet projet = projetRepository.findByNumeroProjet(projetToVerify.getNumeroProjet());
        return projet != null;
    }

    public Boolean deleteProjet(Long id) {
        Optional<Projet> projet = getProjet(id);
        if(projet.isPresent()) {
            projetRepository.delete(projet.get());
            return true;
        }
        return false;
    }

    public Projet addProjet(Projet projet) {
        return projetRepository.save(projet);
    }



}
