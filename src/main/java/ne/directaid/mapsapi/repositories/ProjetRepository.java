package ne.directaid.mapsapi.repositories;

import ne.directaid.mapsapi.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Long>
{
    Projet findByNumeroProjet(String numero);

}
