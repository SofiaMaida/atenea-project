package ar.com.ada.atenea.component;

import ar.com.ada.atenea.exception.ApiEntityError;
import ar.com.ada.atenea.exception.BusinessLogicException;
import ar.com.ada.atenea.model.entity.CourseParticipantId;
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

    public RuntimeException throwExceptionSoldOut(String courseName) {
        ApiEntityError apiEntityError = new ApiEntityError(
                courseName,
                "Not available",
                "There are no vacancies available"
        );

        return new BusinessLogicException(
                "No places available for the course",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }

    public RuntimeException getExceptionApplicationAlreadyExists(CourseParticipantId id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                "CourseApplication",
                "ApplicationAlreadyExists",
                "The application for course id " + id.getCourseId() + " and participant id "
                        + id.getParticipantId() + " already exists"
        );

        return new BusinessLogicException(
                "this application already exists",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }

    public RuntimeException throwExceptionEntityNotFound(String entityName, CourseParticipantId id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The " + entityName + " with course id " + id.getCourseId() + " and participant id "
                        + id.getParticipantId() + " does not exist"
        );

        return new BusinessLogicException(
                entityName + " does not exists",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );

    }
}
