package br.com.hroauth.services;

import br.com.hroauth.clients.OauthUserFeignClient;
import br.com.hroauth.entities.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class OauthUserDetailsService implements UserDetails, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(OauthUserDetailsService.class);

    private final OauthUserFeignClient userFeignClient;

    private String password;

    private String userName;

    private Collection<GrantedAuthority> authorities = new HashSet<>(0);

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User loaded = userRecovery(userName);
        this.password = loaded.getPassword();
        this.userName = loaded.getName();
        this.authorities.addAll(loaded.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet()));
        return this;
    }

    private User userRecovery (String userName) {
        User user = this.userFeignClient
                .findByEmail(userName)
                .getBody();

        userFound(user);

        return user;
    }

    private void userFound (User user) {
        if (Objects.isNull(user)) {
            log.error("Email not found");
            throw new UsernameNotFoundException ("User not found by email");
        }
    }
}