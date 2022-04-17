package br.com.hroauth.services;

import br.com.hroauth.clients.OauthUserFeignClient;
import br.com.hroauth.entities.User;
import br.com.hroauth.exception.FindUserByEmailException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class OauthUserService {

    private static final Logger log = LoggerFactory.getLogger(OauthUserService.class);

    private final OauthUserFeignClient userFeignClient;

    public User findByEmail (String email) {
        User user = this.userFeignClient
                .findByEmail(email)
                .getBody();

        userValid(user);
        log.info("User {} localized by email {}", user.getName(), user.getEmail());
        return user;
    }

    private void userValid (User user) {
        if (user == null) {
            log.error("Email not found");
            throw new FindUserByEmailException("User not found");
        }
    }
}