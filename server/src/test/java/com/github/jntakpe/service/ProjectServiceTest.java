package test.java.com.github.jntakpe.service;

import main.java.com.github.jntakpe.config.ActuatorUiConfig;
import main.java.com.github.jntakpe.domain.Project;
import main.java.com.github.jntakpe.util.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
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
    private ProjectService projectService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeClass
    public void setUp() {
        mongoTemplate.dropCollection(Project.class);
        Project aui = new Project();
        aui.setName("AUI");
        Project tft = new Project();
        tft.setName("TFT");
        mongoTemplate.save(aui);
        mongoTemplate.save(tft);
    }

    @Test
    public void findAllTest_ShouldFind() {
        assertThat(projectService.findAll()).hasSize(2);
    }


}
