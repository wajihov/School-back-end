package fr.anywr.school.core.rest;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Codes {

    ERR_STUDENT_NOT_FOUND("STUDENT NOT FOUND", HttpStatus.NOT_FOUND),
    ERR_STUDENTS_NOT_FOUND("STUDENTS NOT FOUND", HttpStatus.NOT_FOUND),

    ERR_AUTHENTICATE_NOT_FOUND("AUTHENTICATE NOT FOUND", HttpStatus.NOT_FOUND),
    ERR_AUTHENTICATE_EMAIL_EXIST("EMAIL ALREADY EXIST", HttpStatus.NOT_ACCEPTABLE),
    ERR_AUTHENTICATES_NOT_FOUND("AUTHENTICATES NOT FOUND", HttpStatus.NOT_FOUND),

    ERR_CLASSROOM_NOT_FOUND("CLASSROOM NOT FOUND", HttpStatus.NOT_FOUND),
    ERR_CLASSROOMS_NOT_FOUND("CLASSROOMS NOT FOUND", HttpStatus.NOT_FOUND),

    ERR_TEACHER_NOT_FOUND("TEACHER NOT FOUND", HttpStatus.NOT_FOUND),
    ERR_TEACHERS_NOT_FOUND("TEACHERS NOT FOUND", HttpStatus.NOT_FOUND);


    private final String message;
    private final HttpStatus httpStatus;

    Codes(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
