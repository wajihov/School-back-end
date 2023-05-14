package fr.anywr.school.domain.classroom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("/classrooms")
    public ResponseEntity<ClassroomDto> createClassroom(@RequestBody ClassroomDto classroomDto) {
        ClassroomDto dto = classroomService.createClassroom(classroomDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/classrooms/{id}")
    public ResponseEntity<ClassroomDto> searchClassroomById(@PathVariable(name = "id") Long id) {
        ClassroomDto classroomDto = classroomService.getClassroomById(id);
        return new ResponseEntity<>(classroomDto, HttpStatus.OK);
    }


    @GetMapping("/classrooms")
    public ResponseEntity<List<ClassroomDto>> customers() {
        List<ClassroomDto> classroomDtoList = classroomService.getAllClassrooms();
        return new ResponseEntity<>(classroomDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/classrooms/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable(name = "id") Long id) {
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/classrooms/{id}")
    public ResponseEntity<ClassroomDto> updateCustomer(@RequestBody ClassroomDto classroomDto, @PathVariable(name = "id") Long id) {
        ClassroomDto dto = classroomService.updateClassroom(id, classroomDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}