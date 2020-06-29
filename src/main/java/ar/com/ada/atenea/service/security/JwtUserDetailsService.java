package ar.com.ada.atenea.service.security;

import ar.com.ada.atenea.component.security.JwtAuthProvider;
import ar.com.ada.atenea.model.dto.security.JwtUserDetails;
import ar.com.ada.atenea.model.entity.security.User;
import ar.com.ada.atenea.model.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("jwtAuthProvider")
    private JwtAuthProvider jwtAuthProvider;

    //Carga el usuario a base del nombre del usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar en la BD nuestro usuario por el username
        User user = userRepository.findByUsername(username)
                //Si no lo encuentra, lanza una excepcion
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));

        //Se crea el UserDetails con ese user, agarra entity y lo convierto bajo este metodo
        JwtUserDetails jwtUserDetails = jwtAuthProvider.createJwtUserDetails(user);

        return jwtUserDetails;
    }
}
