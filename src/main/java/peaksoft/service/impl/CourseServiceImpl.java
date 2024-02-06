package peaksoft.service.impl;

import peaksoft.dao.Coursedao;
import peaksoft.dao.impl.CourseDaoImpl;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {

    Coursedao coursedao = new CourseDaoImpl();
    @Override
    public void save(Course course) {
        coursedao.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return coursedao.getCourseById(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
     coursedao.updateCourse(id,course);
    }

    @Override
    public void deleteCourseById(Long id) {
        coursedao.deleteCourseById(id);
    }

    @Override
    public Map<Course, List<Student>> getCourseStudents(Long id) {
        return coursedao.getCourseStudents(id);
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
     coursedao.assignStudentToCourse(studentId,courseId);
    }
}
