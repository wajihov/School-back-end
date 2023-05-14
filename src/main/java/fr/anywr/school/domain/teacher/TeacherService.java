package fr.anywr.school.domain.teacher;


import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }


    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        log.info("teacher = {} ", teacher);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    private Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(()
                -> new SchoolException(Codes.ERR_TEACHER_NOT_FOUND));
    }

    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = findTeacherById(id);
        log.info("The teacher searched is {}", teacher.getFirstName());
        return teacherMapper.toDto(teacher);
    }

    List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        log.info("List {} Teachers", teachers.size());
        return teacherMapper.teacherDtoList(teachers);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = findTeacherById(id);
        if (teacher != null) {
            log.info("The deletion of the teacher name {} is successful", teacher.getFirstName());
            teacherRepository.deleteById(id);
        }
    }

    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        teacher.setId(id);
        teacher = teacherRepository.save(teacher);
        log.info("the Teacher with id {} has been successfully modified", teacher.getId());
        return teacherMapper.toDto(teacher);
    }

}
