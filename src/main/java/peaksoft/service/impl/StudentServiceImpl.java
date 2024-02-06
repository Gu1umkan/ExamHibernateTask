package peaksoft.service.impl;

import peaksoft.dao.StudentDao;
import peaksoft.dao.impl.StudentDaoImpl;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public void updateStudentName(Long id, String name) {
     studentDao.updateStudentName(id,name);
    }

    @Override
    public void deleteStudent(Long id) {
     studentDao.deleteStudent(id);
    }

    @Override
    public List<Course> getStudentCourses(Long id) {
        return studentDao.getStudentCourses(id);
    }

    @Override
    public List<Student> getStudentsByRecentEnrollments(int dateLimit) {
        return studentDao.getStudentsByRecentEnrollments(dateLimit);
    }
}
