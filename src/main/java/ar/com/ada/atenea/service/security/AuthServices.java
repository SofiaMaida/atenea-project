package ar.com.ada.atenea.service.security;

import ar.com.ada.atenea.component.security.JwtAuthProvider;
import ar.com.ada.atenea.model.dto.security.JwtAuthRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("authServices")
public class AuthServices {

    @Autowired
    @Qualifier("jwtAuthProvider")
    private JwtAuthProvider jwtAuthProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Captura la info que viene del RequestBody, y se va a encargar en hacer el token
    public String jwtNewToken(JwtAuthRequestBody body) {
        //Crear un objeto de tipo UserPasswordAuthToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                body.getUsername(), body.getPassword());

        //Despues de crear este objeto (necesario p/el core), el core necesita el AuthManager p/crear la auth
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //Agregar esa autenticacion al contexto de la aplicacion
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Se necesita obtener el detalle del usuario ("getPrincipal()")
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //Generar el token
        String generateToken = jwtAuthProvider.doGenerateToken(userDetails);

        return generateToken;


    }

}
