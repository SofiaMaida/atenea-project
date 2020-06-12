package ar.com.ada.atenea.component.data;

import ar.com.ada.atenea.model.entity.Gender;
import ar.com.ada.atenea.model.repository.GenderRepository;
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
public class GenderLoaderData implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(GenderLoaderData.class);

    @Autowired @Qualifier("genderRepository")
    private GenderRepository genderRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Enviroment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {

            LOGGER.info("Loading gender initial data... ");

            List<Gender> genderList = Arrays.asList(
                    new Gender("Femenino"),
                    new Gender("Masculino"),
                    new Gender("Prefiero no decirlo")
            );
            genderList.forEach(gender -> genderRepository.save(gender));
        }

    }

}
