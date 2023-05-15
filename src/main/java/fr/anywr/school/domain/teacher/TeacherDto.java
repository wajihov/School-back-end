package fr.anywr.school.domain.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;

}