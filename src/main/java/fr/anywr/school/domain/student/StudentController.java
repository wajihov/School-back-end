package fr.anywr.school.domain.student;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createStudentDto(@RequestBody StudentDto studentDto) {
        StudentDto dto = studentService.createStudent(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> searchStudentById(@PathVariable(name = "id") Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("/students/all")
    public ResponseEntity<List<StudentDto>> students() {
        List<StudentDto> studentDtoList = studentService.getAllStudents();
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, @PathVariable(name = "id") Long id) {
        StudentDto dto = studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public Page<Student> getFilteredStudents(
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String teacherName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {
        return studentService.getFilteredStudents(className, teacherName, page, pageSize);
    }

}
