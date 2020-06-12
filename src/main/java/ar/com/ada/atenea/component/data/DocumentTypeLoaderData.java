package ar.com.ada.atenea.component.data;

import ar.com.ada.atenea.model.entity.DocumentType;
import ar.com.ada.atenea.model.entity.Gender;
import ar.com.ada.atenea.model.repository.DocumentTypeRepository;
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
public class DocumentTypeLoaderData implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(GenderLoaderData.class);

    @Autowired @Qualifier("documentTypeRepository")
    private DocumentTypeRepository documentTypeRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Enviroment: " + appEnv);

        if (appEnv.equals("dev") || appEnv.equals("qa")) {

            LOGGER.info("Loading document type initial data... ");

            List<DocumentType> documentTypeList = Arrays.asList(
                    new DocumentType("DNI"),
                    new DocumentType("CUIL"),
                    new DocumentType("Pasaporte"),
                    new DocumentType("Extranjero")
                    );
            documentTypeList.forEach(documentType -> documentTypeRepository.save(documentType));
        }

    }

}
