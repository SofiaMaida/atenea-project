package ar.com.ada.atenea.component.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("restAuthEntryPoint")
public class RestAuthEntryPoint implements AuthenticationEntryPoint {
    //Si hay error de autenticacion, se usa esta clase. Solo si hay una excepcion
    //en el core de security

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException, ServletException {
        //"csStatus" = Httpstatus
        Integer scStatus = null;
        //Mensaje del error - Todo en mayuscula - Cambio de espacios por _
        String erorrType = ex.getLocalizedMessage().toLowerCase().replace(" ", "_");

        switch (erorrType) {
            case "BAD_REQUEST":
                //Obetener el estatus del Response (res)
                scStatus = res.SC_BAD_REQUEST;
                break;
            default:
                //No esta autorizado
                scStatus = res.SC_UNAUTHORIZED;

        }
        //Mandar el error -> estatus y el mensaje
        res.sendError(scStatus, "invalid credentials, please check");

    }
}
