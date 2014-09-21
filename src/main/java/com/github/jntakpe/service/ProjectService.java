package com.github.jntakpe.service;

import com.github.jntakpe.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services associés à l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
