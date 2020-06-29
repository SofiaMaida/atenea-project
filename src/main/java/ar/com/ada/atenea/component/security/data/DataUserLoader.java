package ar.com.ada.atenea.component.security.data;

import ar.com.ada.atenea.model.entity.security.Authority;
import ar.com.ada.atenea.model.entity.security.User;
import ar.com.ada.atenea.model.repository.security.AuthorityRepository;
import ar.com.ada.atenea.model.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class DataUserLoader implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataUserLoader.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("authorityRepository")
    private AuthorityRepository authorityRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Value("${spring.application.env}")
    private String appEnv;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("DataUserLoader.run");

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading initial data Users");

            Authority adminAuthority = authorityRepository.findById(1L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            Authority managerAuthority = authorityRepository.findById(2L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            Authority userAuthority = authorityRepository.findById(3L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            User admin = new User()
                    .setId(1L).setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setFirstName("admin")
                    .setLastName("admin").setEmail("admin@admin.com")
                    .setEnabled(true)
                    //admin tiene permiso en los 3 roles: admin, manager, user
                    .addAuthority(adminAuthority)
                    .addAuthority(managerAuthority)
                    .addAuthority(userAuthority);

            User manager = new User()
                    .setId(2L).setUsername("manager")
                    .setPassword(passwordEncoder.encode("manager"))
                    .setFirstName("manager")
                    .setLastName("manager").setEmail("manager@manager.com")
                    .setEnabled(true)
                    //admin tiene permiso en los 2 roles: manager, user
                    .addAuthority(managerAuthority)
                    .addAuthority(userAuthority);

            User user = new User()
                    .setId(2L).setUsername("user")
                    .setPassword(passwordEncoder.encode("user"))
                    .setFirstName("user")
                    .setLastName("user").setEmail("user@user.com")
                    .setEnabled(true)
                    //admin tiene permiso solo en user
                    .addAuthority(userAuthority);

            List<User> userList = Arrays.asList(admin, manager, user);
            userRepository.saveAll(userList);

        }

    }
}
