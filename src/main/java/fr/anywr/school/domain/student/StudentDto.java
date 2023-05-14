package fr.anywr.school.domain.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long classroomId;

}
