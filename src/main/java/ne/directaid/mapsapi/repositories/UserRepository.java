package ne.directaid.mapsapi.repositories;

import ne.directaid.mapsapi.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur, Long>
{
    Utilisateur findByUsername(String username);
    Utilisateur findByTelephone(String telephone);
}
