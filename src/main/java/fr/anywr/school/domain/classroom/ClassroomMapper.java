package fr.anywr.school.domain.classroom;

import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import fr.anywr.school.domain.student.StudentMapper;
import fr.anywr.school.domain.teacher.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomMapper {

    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    public ClassroomMapper(TeacherMapper teacherMapper, StudentMapper studentMapper) {
        this.teacherMapper = teacherMapper;
        this.studentMapper = studentMapper;
    }

    public Classroom toEntity(ClassroomDto classroomDto) {
        if (classroomDto == null) {
            throw new SchoolException(Codes.ERR_CLASSROOM_NOT_FOUND);
        }
        return Classroom.builder()
                .id(classroomDto.getId())
                .name(classroomDto.getName())
                .teacher(teacherMapper.toEntity(classroomDto.getTeacherDto()))
                .build();
    }

    public ClassroomDto toDto(Classroom classroom) {
        if (classroom == null) {
            throw new SchoolException(Codes.ERR_CLASSROOM_NOT_FOUND);
        }
        return ClassroomDto.builder()
                .id(classroom.getId())
                .name(classroom.getName())
                .teacherDto(teacherMapper.toDto(classroom.getTeacher()))
                .studentDtoList(studentMapper.studentDtoList(classroom.getStudents()))
                .build();
    }

    public List<ClassroomDto> classroomDtoList(List<Classroom> classrooms) {
        if (classrooms == null) {
            throw new SchoolException(Codes.ERR_CLASSROOMS_NOT_FOUND);
        }
        return classrooms.stream().map(this::toDto).collect(Collectors.toList());
    }
}