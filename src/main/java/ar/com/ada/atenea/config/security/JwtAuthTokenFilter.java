package ar.com.ada.atenea.config.security;

import ar.com.ada.atenea.component.security.JwtAuthProvider;
import ar.com.ada.atenea.service.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
    //Intercepta los request = filters

    //Valor de donde se extrae el token del req
    @Value("${auth.jwt.header.key}")
    private String authHeaderKey;

    @Autowired
    @Qualifier("jwtAuthProvider")
    private JwtAuthProvider jwtAuthProvider;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        //Extraer del header el token - hash
        //Por cada peticion req (get/post/put..) debe tener su autenticacion
        String authToken = req.getHeader(authHeaderKey);

        //Extrer del token el nombre de usuario
        String username = jwtAuthProvider.getUsernameFromToken(authToken);

        //Verifico: token no este nulo && token sea valido (estrucura Bearer y Jwt) && la autenticacion en el contexto este activada
        if (username != null && jwtAuthProvider.isBearer(authToken) && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Buscar por el username el usuario
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            //Si el token pertenece a este usuario en la BD
            if (jwtAuthProvider.validateToken(authToken, userDetails)) {
                //Creando la autenticacion
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                //Setea los detalles del req a la autenticacion
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //Por cada req va a: obtener de la cabecera el hash,
        //De ese hash el nombre de usuario,
        //Verifica que el token no sea nulo, hash formato correcto, y la aut del contexto sea nulo
        //Busca el usuario en la BD
        //Verifica que el token que se recibe pertenezca a ese usuario
        //De ser asi, genera la autenticacion, setea los detalles en base al req
        //Y se le asigna al contexto.

        //Intercepta el req para verificar
        chain.doFilter(req, res);
    }

}

