package br.com.hroauth.services;

import br.com.hroauth.clients.OauthUserFeignClient;
import br.com.hroauth.entities.User;
import br.com.hroauth.exception.FindUserByEmailException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class OauthUserService {

    private static final Logger log = LoggerFactory.getLogger(OauthUserService.class);

    private final OauthUserDetailsService userDetailsService;

    public User findByEmail (String email) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        return User.builder().build();
    }
}