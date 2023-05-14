package fr.anywr.school.core.exception;

import fr.anywr.school.core.rest.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class SchoolHandler {

    @ExceptionHandler(SchoolException.class)
    public ResponseEntity<ServerResponse> HandlerException(SchoolException ex) {
        ServerResponse serverResponse = ServerResponse
                .builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getCodes().getMessage())
                .build();
        return new ResponseEntity<>(serverResponse, ex.getCodes().getHttpStatus());
    }
}
