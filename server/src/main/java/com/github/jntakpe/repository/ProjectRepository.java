package main.java.com.github.jntakpe.repository;

import main.java.com.github.jntakpe.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Publication des méthodes d'accès aux données de l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
public interface ProjectRepository extends MongoRepository<Project, String> {

}
