package peaksoft.dao;

import peaksoft.entity.Course;
import peaksoft.entity.Student;

import java.util.List;
import java.util.Map;

public interface Coursedao {
    void save(Course course);
    Course getCourseById(Long id);
    void updateCourse(Long id, Course course);
    void deleteCourseById(Long id);

    Map<Course,List<Student>>getCourseStudents(Long id);

    void assignStudentToCourse(Long studentId, Long courseId);
}
