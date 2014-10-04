package com.github.jntakpe.web;

import com.github.jntakpe.domain.Project;
import com.github.jntakpe.service.ProjectService;
import com.github.jntakpe.service.UserService;
import com.github.jntakpe.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    private static final String LIST_VIEW_PATH = "projects";

    private static final String EDIT_VIEW_PATH = "edit-project";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Affiche la vue de gestion des projets
     *
     * @return vue de gestion des projets
     */
    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return LIST_VIEW_PATH;
    }

    /**
     * Récupère la liste des projets
     *
     * @return liste des projets
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Project> findAll() {
        return projectService.findAll();
    }

    /**
     * Affiche la vue de création ou de modification d'un projet avec le projet à créer ou modifier
     *
     * @param id identifiant du projet. Dans le cas d'un nouveau projet {@code id} est égal à {@link Constants#NEW_ID}
     * @return la vue de création ou de modification d'une projet
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView displayEdit(@PathVariable String id) {
        Project project = Constants.NEW_ID.equals(id) ? new Project() : projectService.findOne(id);
        return new ModelAndView(EDIT_VIEW_PATH).addObject(project);
    }

    /**
     * Créé un nouveau projet et affiche la vue de gestion des projets
     *
     * @param project projet à créé
     * @return vue de gestion des projets
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid Project project) {
        project = projectService.save(project);
        LOGGER.debug("L'utilisateur {} a créé le projet {}", UserService.getCurrentUser(), project.getName());
        return new ModelAndView(LIST_VIEW_PATH);
    }


}
