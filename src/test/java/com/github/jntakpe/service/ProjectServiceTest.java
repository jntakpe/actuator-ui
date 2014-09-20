package com.github.jntakpe.service;

import com.github.jntakpe.config.ActuatorUiConfig;
import com.github.jntakpe.domain.Project;
import com.github.jntakpe.repository.ProjectRepository;
import com.github.jntakpe.util.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests associés aux services relatifs à l'entité {@link com.github.jntakpe.domain.Project}
 *
 * @author jntakpe
 */
@SpringApplicationConfiguration(classes = ActuatorUiConfig.class)
@ActiveProfiles(value = Profile.Constant.DEVELOPPEMENT)
public class ProjectServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProjectRepository projectRepository;

    //TODO to delete
    @Test
    public void mongoTest() {
        projectRepository.deleteAll();
        Project aui = new Project("AUI", "SP", "1.0.0", "htttp///sdf");
        Project sp = new Project("eers", "ss", "1.0.0", "htttp///sdf");
        projectRepository.save(aui);
        projectRepository.save(sp);
        assertThat(projectRepository.count()).isEqualTo(2L);
        assertThat(projectRepository.findAll()).contains(aui).contains(sp);
        projectRepository.deleteAll();
    }

}
