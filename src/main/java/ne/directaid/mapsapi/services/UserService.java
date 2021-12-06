package ne.directaid.mapsapi.services;


import lombok.AllArgsConstructor;
import ne.directaid.mapsapi.models.Projet;
import ne.directaid.mapsapi.models.Utilisateur;
import ne.directaid.mapsapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public Iterable<Utilisateur> getUsers() {
        return userRepository.findAll();
    }

    public Optional<Utilisateur> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Utilisateur login(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean userExists(Utilisateur user) {
        Utilisateur user1 = userRepository.findByTelephone(user.getTelephone());
        System.out.println("================"+user1 !=null);
        return user1 !=null;
    }

    public Boolean deleteUser(Long id) {
        Optional<Utilisateur> user = getUser(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    public Utilisateur saveUser(Utilisateur user) {
        return userRepository.save(user);
    }
}
