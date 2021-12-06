package ne.directaid.mapsapi.controllers;


import lombok.AllArgsConstructor;
import ne.directaid.mapsapi.dto.Auth;
import ne.directaid.mapsapi.exceptions.RequestException;
import ne.directaid.mapsapi.models.Utilisateur;
import ne.directaid.mapsapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController
{
    private UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value="/")
    public ResponseEntity<Iterable<Utilisateur>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<Utilisateur>> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUtilisateur(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<Object> saveUtilisateur(@Valid @RequestBody Utilisateur user) {
        ResponseEntity<Object> response;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try
        {
            if(Boolean.TRUE.equals(userService.userExists(user)))
                throw new RequestException("Ce numéro de téléphone existe déjà !");
            else response = ResponseEntity.ok(userService.saveUser(user));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return response;
    }

    @PostMapping(value="/login")
    public ResponseEntity<Object> login(@Valid @RequestBody Auth authUser) {
        Utilisateur user1 = userService.login(authUser.getUsername());
        // System.out.println(user1.toString());
        ResponseEntity<Object> response;
        try {
            if(user1 != null) {
                if(!passwordEncoder.matches(authUser.getPassword(), user1.getPassword())) {
                    throw new RequestException("Le mot de passe saisi est incorrect");
                }  else {

                    response = ResponseEntity.ok(user1);
                }
            } else {
                throw new RequestException("Ce login n'existe pas.");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            System.out.println(response);
        }
        return response;
    }
}
