package com.github.jntakpe.web;

import com.github.jntakpe.domain.Project;
import com.github.jntakpe.service.ProjectService;
import com.github.jntakpe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Contrôlleur gérant les requêtes relatives à l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    public static final String VIEW_PATH = "projects";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return VIEW_PATH;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Project create(@Valid Project project) {
        project = projectService.save(project);
        LOGGER.debug("L'utilisateur {} a créé le projet {}", UserService.getCurrentUser(), project.getName());
        return project;
    }
}
