package com.github.jntakpe.repository;

import com.github.jntakpe.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Publication des méthodes d'accès aux données pour l'entité {@link com.github.jntakpe.domain.User}
 *
 * @author jntakpe
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Retrouve un {@link User} en fonction de son login quelque soit la case
     *
     * @param login login de l'utilisateur
     * @return utilisateur
     */
    User findByLoginIgnoreCase(String login);

    /**
     * Retrouve un {@link User} en fonction de son mail quelque soit la case
     *
     * @param email mail de l'utilisateur
     * @return utilisateur
     */
    User findByEmailIgnoreCase(String email);
}
