package com.github.jntakpe.service;

import com.github.jntakpe.domain.User;
import com.github.jntakpe.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Services associés à l'entité
 *
 * @author jntakpe
 */
@Service
public class UserService implements UserDetailsService {

    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authentifie un utilisateur
     *
     * @param login login de l'utilisateur à authentifier
     * @return l'utilisateur authentifié
     * @throws UsernameNotFoundException si le {@code login} n'est pas trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        LOG.debug("Tentative d'authentification pour l'utilisateur '{}'", login);
        User user = userRepository.findByLoginIgnoreCase(login);
        if (user == null) {
            throw new UsernameNotFoundException("L'utilisateur " + login + " est introuvable");
        }
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        LOG.debug("Authentification de l'utilisateur '{}' avec les droits : {}", user.getLogin(), roles);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }

    /**
     * Créer un nouvel utilisateur
     *
     * @param user utilisateur à créer
     * @return l'utilisateur créé
     */
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User persistedUser = userRepository.save(user);
        LOG.info("Création de l'utilisateur {}", user.getLogin());
        return persistedUser;
    }
}
