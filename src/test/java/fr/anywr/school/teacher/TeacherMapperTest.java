package fr.anywr.school.teacher;

import fr.anywr.school.domain.teacher.Teacher;
import fr.anywr.school.domain.teacher.TeacherDto;
import fr.anywr.school.domain.teacher.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void GIVEN_Teacher_WHEN_ToDto_THEN_should_return_TeacherDto() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Alaa");
        teacher.setLastName("Borbala");
        // WHEN
        TeacherDto teacherDto = teacherMapper.toDto(teacher);
        // THEN
        assertEquals(teacherDto.getId(), teacher.getId());
        assertEquals(teacherDto.getLastName(), teacher.getLastName());
        assertEquals(teacherDto.getFirstName(), teacher.getFirstName());
    }

    @Test
    void GIVEN_TeacherDto_WHEN_ToEntity_THEN_should_return_Teacher() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setFirstName("Alaa");
        teacherDto.setLastName("Borbala");
        // WHEN
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        // THEN
        assertEquals(teacherDto.getId(), teacher.getId());
        assertEquals(teacherDto.getLastName(), teacher.getLastName());
        assertEquals(teacherDto.getFirstName(), teacher.getFirstName());
    }

    @Test
    void GIVEN_Teachers_WHEN_teacherDtoList_THEN_Return_ListOfTeacherDto() {
        Teacher teacher1 = Teacher.builder()
                .id(1L)
                .lastName("Farid")
                .firstName("Bani")
                .build();

        Teacher teacher2 = Teacher.builder()
                .id(2L)
                .lastName("Farah")
                .firstName("Haddad")
                .build();

        List<Teacher> listTeachers = new ArrayList<>();
        listTeachers.add(teacher1);
        listTeachers.add(teacher2);
        //WHEN
        List<TeacherDto> dtoList = teacherMapper.teacherDtoList(listTeachers);
        //THEN
        assertEquals(listTeachers.size(), dtoList.size());
        for (int i = 0; i < dtoList.size(); i++) {
            TeacherDto teacherDto = dtoList.get(i);
            Teacher teacher = listTeachers.get(i);

            assertEquals(teacher.getId(), teacherDto.getId());
            assertEquals(teacher.getLastName(), teacherDto.getLastName());
            assertEquals(teacher.getFirstName(), teacherDto.getFirstName());
        }
    }
}
