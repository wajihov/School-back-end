package fr.anywr.school.domain.classroom;

import fr.anywr.school.domain.student.StudentDto;
import fr.anywr.school.domain.teacher.Teacher;
import fr.anywr.school.domain.teacher.TeacherDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClassroomDto {

    private Long id;
    private String name;
    private TeacherDto teacherDto;
    private List<StudentDto> studentDtoList;
}
