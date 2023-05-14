package fr.anywr.school.domain.student;

import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {

    public Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            throw new SchoolException(Codes.ERR_STUDENT_NOT_FOUND);
        }
        return Student.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .build();
    }

    public StudentDto toDto(Student student) {
        if (student == null) {
            throw new SchoolException(Codes.ERR_STUDENT_NOT_FOUND);
        }
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .classroomId(student.getClassroom().getId())
                .build();
    }

    public List<StudentDto> studentDtoList(List<Student> students) {
        if (students == null) {
            throw new SchoolException(Codes.ERR_STUDENTS_NOT_FOUND);
        }
        return students.stream().map(this::toDto).collect(Collectors.toList());
    }
}