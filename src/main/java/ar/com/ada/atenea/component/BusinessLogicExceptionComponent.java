package ar.com.ada.atenea.component;

import ar.com.ada.atenea.exception.ApiEntityError;
import ar.com.ada.atenea.exception.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public RuntimeException throwExceptionEntityNotFound(String entityName, Long id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "Not found",
                "The " + entityName + " with id '" + id + "' does not exist"
        );

        return new BusinessLogicException(
                entityName + " does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }
}
