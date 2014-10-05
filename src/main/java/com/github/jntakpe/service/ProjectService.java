package com.github.jntakpe.service;

import com.github.jntakpe.domain.Project;
import com.github.jntakpe.dto.Metrics;
import com.github.jntakpe.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Services associés à l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
@Service
public class ProjectService extends MongoAbstractService<Project> {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
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
