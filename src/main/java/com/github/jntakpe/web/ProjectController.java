package com.github.jntakpe.web;

import com.github.jntakpe.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Contrôlleur gérant les requêtes relatives à l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    public static final String VIEW_PATH = "projects";

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return VIEW_PATH;
    }
}
