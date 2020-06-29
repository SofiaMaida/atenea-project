package ar.com.ada.atenea.advice.security;

import ar.com.ada.atenea.exception.ApiErrorsResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Collections;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class RestSecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleBusinessLogicException(AccessDeniedException ex, NativeWebRequest req) {

        HttpStatus httpStatus = UNAUTHORIZED;

        ApiErrorsResponseBody apiErrorsResponseBody = new ApiErrorsResponseBody<>(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                Collections.singletonList(ex.getMessage()));

        return ResponseEntity
                .status(httpStatus)
                .body(apiErrorsResponseBody);
    }
}
