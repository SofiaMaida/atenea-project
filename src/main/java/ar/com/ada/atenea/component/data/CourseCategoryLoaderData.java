package ar.com.ada.atenea.component.data;

import ar.com.ada.atenea.model.entity.CourseCategory;
import ar.com.ada.atenea.model.repository.CourseCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CourseCategoryLoaderData implements ApplicationRunner {

    //Saber el valor de la variable
    public static final Logger LOGGER = LoggerFactory.getLogger(CourseCategoryLoaderData.class);

    @Autowired @Qualifier("courseCategoryRepository")
    private CourseCategoryRepository courseCategoryRepository;

    //Ambiente de aplicacion - obtener el valor de la variable
    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Enviroment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {

            LOGGER.info("Loading course category initial data... ");

            List<CourseCategory> courseCategoryList = Arrays.asList(
                    new CourseCategory().setId(1L).setTypeCategory("IT"),
                    new CourseCategory().setId(2L).setTypeCategory("Idiomas"),
                    new CourseCategory().setId(3L).setTypeCategory("Alimentacion"),
                    new CourseCategory().setId(4L).setTypeCategory("Turismo"),
                    new CourseCategory().setId(5L).setTypeCategory("Musica")
            );
            //courseCategoryRepository.saveAll(courseCategoryList);
            courseCategoryList.forEach(courseCategory -> courseCategoryRepository.save(courseCategory));
        }

    }
}
