package fr.anywr.school.domain.teacher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;

}