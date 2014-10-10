package com.github.jntakpe.service;

import com.github.jntakpe.domain.Metrics;
import com.github.jntakpe.domain.Project;
import com.github.jntakpe.repository.ProjectRepository;
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


    @Override
    public MongoRepository<Project, String> getMongoRepository() {
        return projectRepository;
    }

    public String testActuator(String url) {
        RestTemplate restTemplate = new RestTemplate();
        Metrics test = restTemplate.getForObject(url, Metrics.class);
        return "yeah";
    }

    public Metrics findMetrics(String url) {
        RestTemplate restTemplate = new RestTemplate();
        Metrics metrics = restTemplate.getForObject(url, Metrics.class);
        return metrics;
    }
}
