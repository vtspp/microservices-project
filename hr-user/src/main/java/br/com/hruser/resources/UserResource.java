package br.com.hruser.resources;

import br.com.hruser.entities.User;
import br.com.hruser.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/v1/users")
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class UserResource {

    private final UserRepository repository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById (@PathVariable Long id) {
        return ResponseEntity.ok(this.repository.findById(id).get());
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail (@RequestParam String email) {
        return ResponseEntity.ok(this.repository.findByEmail(email));
    }
}