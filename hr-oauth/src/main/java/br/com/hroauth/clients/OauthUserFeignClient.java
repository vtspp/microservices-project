package br.com.hroauth.clients;

import br.com.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient (name = "hr-user", path = "/v1/users")
public interface OauthUserFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<User> findByEmail (@RequestParam String email);

}