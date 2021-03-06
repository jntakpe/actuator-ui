package com.github.jntakpe.service;

import com.github.jntakpe.domain.User;
import com.github.jntakpe.exception.AuiException;
import com.github.jntakpe.exception.FunctionnalCode;
import com.github.jntakpe.repository.UserRepository;
import com.github.jntakpe.util.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        LOG.debug("Authentification de l'utilisateur '{}'", user.getLogin());
        Date lastConnection = user.getLastConnection();
        user.setLastConnection(new Date());
        userRepository.save(user);
        return new SecurityUser(user.getLogin(), user.getPassword(), user.getId(), lastConnection);
    }

    /**
     * Créé un nouvel utilisateur
     *
     * @param user utilisateur à créer
     * @return l'utilisateur créé
     */
    public User create(User user) {
        if (findByLoginIgnoreCase(user.getLogin()) != null) {
            throw AuiException.createInstance(FunctionnalCode.UNIQUE_CONSTRAINT_VIOLATION, user.getLogin(), "login");
        }
        if (findByEmailIgnoreCase(user.getEmail()) != null) {
            throw AuiException.createInstance(FunctionnalCode.UNIQUE_CONSTRAINT_VIOLATION, user.getEmail(), "email");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        LOG.info("Création de l'utilisateur {}", user.getLogin());
        return user;
    }

    /**
     * Récupère un {@link com.github.jntakpe.domain.User} en fonction de son login quelque soit la case
     *
     * @param login login de l'utilisateur recherché
     * @return l'utilisateur correspondant au login
     */
    public User findByLoginIgnoreCase(String login) {
        return userRepository.findByLoginIgnoreCase(login);
    }

    /**
     * Récupère un {@link com.github.jntakpe.domain.User} en fonction de son mail quelque soit la case
     *
     * @param email mail de l'utilisateur
     * @return l'utilisateur correspondant au mail
     */
    public User findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    /**
     * Renvoie l'utilisateur courant
     *
     * @return {@link com.github.jntakpe.util.SecurityUser} courant
     */
    public static SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
