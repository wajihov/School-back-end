package fr.anywr.school.domain.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherDto dto = teacherService.createTeacher(teacherDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> searchTeacherById(@PathVariable(name = "id") Long id) {
        TeacherDto teacherDto = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> teachers() {
        List<TeacherDto> teacherDtoList = teacherService.getAllTeachers();
        return new ResponseEntity<>(teacherDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable(name = "id") Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/teachers/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable(name = "id") Long id) {
        TeacherDto dto = teacherService.updateTeacher(id, teacherDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
