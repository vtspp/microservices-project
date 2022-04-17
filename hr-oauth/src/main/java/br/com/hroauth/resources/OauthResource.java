package br.com.hroauth.resources;

import br.com.hroauth.entities.User;
import br.com.hroauth.services.OauthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/oauth")
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class OauthResource {

    private final OauthUserService oauthUserService;

    @GetMapping (value = "/search")
    public ResponseEntity<User> findByEmail (@RequestParam String email) {
        return ResponseEntity.ok(this.oauthUserService.findByEmail(email));
    }
}