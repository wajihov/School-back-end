package fr.anywr.school.domain.student;

import fr.anywr.school.core.exception.SchoolException;
import fr.anywr.school.core.rest.Codes;
import fr.anywr.school.domain.classroom.Classroom;
import fr.anywr.school.domain.classroom.ClassroomDto;
import fr.anywr.school.domain.classroom.ClassroomMapper;
import fr.anywr.school.domain.classroom.ClassroomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClassroomService classroomService;
    private final ClassroomMapper classroomMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, ClassroomService classroomService, ClassroomMapper classroomMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classroomService = classroomService;
        this.classroomMapper = classroomMapper;
    }

    private Classroom findClassroom(Long id) {
        ClassroomDto classroomDto = classroomService.getClassroomById(id);
        return classroomMapper.toEntity(classroomDto);
    }


    public StudentDto createStudent(StudentDto studentDto) {
        Long classroomId = studentDto.getClassroomId();
        Classroom classroom = findClassroom(classroomId);

        Student student = studentMapper.toEntity(studentDto);
        student.setClassroom(classroom);
        log.info("student = {} ", student);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new SchoolException(Codes.ERR_STUDENT_NOT_FOUND));
    }

    public StudentDto getStudentById(Long id) {
        Student student = findStudentById(id);
        log.info("The student searched is {}", student.getFirstName());
        return studentMapper.toDto(student);
    }

    List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        log.info("List {} students", students.size());
        return studentMapper.studentDtoList(students);
    }

    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        if (student != null) {
            log.info("The deletion of the Student name {} is successful", student.getFirstName());
            studentRepository.deleteById(id);
        }
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Long classroomId = studentDto.getClassroomId();
        Classroom classroom = findClassroom(classroomId);
        Student student = studentMapper.toEntity(studentDto);
        student.setId(id);
        student.setClassroom(classroom);
        student = studentRepository.save(student);
        log.info("The Student with id {} has been successfully modified", student.getId());
        return studentMapper.toDto(student);
    }

    public Page<Student> getFilteredStudents(String className, String teacherName, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return studentRepository.findByClassroomAndTeacher(className, teacherName, pageable);
    }

}
