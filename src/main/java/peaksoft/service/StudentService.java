package peaksoft.service;

import peaksoft.entity.Course;
import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student student);
    Student getStudentById(Long id);
    void updateStudentName(Long id, String name);
    void deleteStudent(Long id);

    List<Course> getStudentCourses(Long id);
    List<Student> getStudentsByRecentEnrollments(int dateLimit);
}
