package fr.anywr.school.domain.teacher;

import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherMapper {

    public Teacher toEntity(TeacherDto teacherDto) {
        if (teacherDto == null) {
            throw new SchoolException(Codes.ERR_TEACHER_NOT_FOUND);
        }
        return Teacher.builder()
                .id(teacherDto.getId())
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .build();
    }

    public TeacherDto toDto(Teacher teacher) {
        if (teacher == null) {
            throw new SchoolException(Codes.ERR_TEACHER_NOT_FOUND);
        }
        return TeacherDto.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .build();
    }

    public List<TeacherDto> teacherDtoList(List<Teacher> teachers) {
        if (teachers == null) {
            throw new SchoolException(Codes.ERR_TEACHERS_NOT_FOUND);
        }
        return teachers.stream().map(this::toDto).collect(Collectors.toList());
    }
}