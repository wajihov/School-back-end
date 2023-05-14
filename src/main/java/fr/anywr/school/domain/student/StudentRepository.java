package fr.anywr.school.domain.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAllByClassroom_NameOrClassroom_Teacher_Id(Pageable pageable, String classroomName, Long teacherId);

    @Query("SELECT s FROM Student s " +
            "JOIN s.classroom c " +
            "JOIN c.teacher t " +
            "WHERE (:className IS NULL OR c.name = :className) " +
            "AND (:teacherName IS NULL OR CONCAT(t.firstName, ' ', t.lastName) = :teacherName)")
    Page<Student> findByClassroomAndTeacher(@Param("className") String className, @Param("teacherName") String teacherName, Pageable pageable);
}
