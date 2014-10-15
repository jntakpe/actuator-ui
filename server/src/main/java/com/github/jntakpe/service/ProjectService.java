package main.java.com.github.jntakpe.service;

import main.java.com.github.jntakpe.domain.Project;
import main.java.com.github.jntakpe.dto.InfoEndpoint;
import com.github.jntakpe.repository.ProjectRepository;
import main.java.com.github.jntakpe.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Services associés à l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
@Service
public class ProjectService implements MongoService<Project> {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Récupère la version d'un projet à l'aide de l'url d'info
     *
     * @param url url d'info
     * @return la version du projet
     */
    public String findVersion(String url) {
        return new RestTemplate().getForObject(url, InfoEndpoint.class).getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MongoRepository<Project, String> getMongoRepository() {
        return projectRepository;
    }
}
