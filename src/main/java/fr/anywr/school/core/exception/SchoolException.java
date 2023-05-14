package fr.anywr.school.core.exception;

import fr.anywr.school.core.rest.Codes;
import lombok.Getter;

@Getter
public class SchoolException extends RuntimeException {

    private Codes codes;

    public SchoolException(Codes codes) {
        super(codes.getMessage());
        this.codes = codes;
    }
}
