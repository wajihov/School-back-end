package fr.anywr.school.domain.classroom;

import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import fr.anywr.school.domain.teacher.TeacherDto;
import fr.anywr.school.domain.teacher.TeacherMapper;
import fr.anywr.school.domain.teacher.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final TeacherMapper teacherMapper;
    private final TeacherService teacherService;

    public ClassroomService(ClassroomRepository classroomRepository, ClassroomMapper classroomMapper, TeacherMapper teacherMapper, TeacherService teacherService) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.teacherMapper = teacherMapper;
        this.teacherService = teacherService;
    }

    public ClassroomDto createClassroom(ClassroomDto classroomDto) {
        TeacherDto teacherDto = classroomDto.getTeacherDto();
        teacherDto = teacherService.createTeacher(teacherDto);

        Classroom classroom = classroomMapper.toEntity(classroomDto);
        classroom.setTeacher(teacherMapper.toEntity(teacherDto));
        classroom = classroomRepository.save(classroom);
        log.info("classroom = {} ", classroom.getName());
        return classroomMapper.toDto(classroom);
    }

    private Classroom findClassroomById(Long id) {
        return classroomRepository.findById(id).orElseThrow(()
                -> new SchoolException(Codes.ERR_CLASSROOM_NOT_FOUND));
    }

    public ClassroomDto getClassroomById(Long id) {
        Classroom classroom = findClassroomById(id);
        log.info("The classroom searched is {}", classroom.getName());
        return classroomMapper.toDto(classroom);
    }

    List<ClassroomDto> getAllClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();
        log.info("List {} classrooms", classrooms.size());
        return classroomMapper.classroomDtoList(classrooms);
    }

    public void deleteClassroom(Long id) {
        Classroom classroom = findClassroomById(id);
        if (classroom != null) {
            log.info("The deletion of the classroom name {} is successful", classroom.getName());
            classroomRepository.deleteById(id);
        }
    }

    public ClassroomDto updateClassroom(Long id, ClassroomDto classroomDto) {
        TeacherDto teacherDto = classroomDto.getTeacherDto();
        teacherDto = teacherService.updateTeacher(teacherDto.getId(), teacherDto);
        Classroom classroom = classroomMapper.toEntity(classroomDto);
        classroom.setTeacher(teacherMapper.toEntity(teacherDto));
        classroom.setId(id);
        classroom = classroomRepository.save(classroom);
        log.info("the Classroom with id {} has been successfully modified", classroom.getId());
        return classroomMapper.toDto(classroom);
    }


}
