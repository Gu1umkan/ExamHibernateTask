package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.connection.DatabaseConnection;
import peaksoft.dao.Coursedao;
import peaksoft.entity.Course;
import peaksoft.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDaoImpl implements Coursedao {
    EntityManagerFactory entityManagerFactory = DatabaseConnection.entityManagerFactory();

    @Override
    public void save(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(course);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        System.out.println(course.getName() + " saved");
    }

    @Override
    public Course getCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        try {
            entityManager.getTransaction().begin();

            course = entityManager.find(Course.class, id);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        return course;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
           Course oldCourse = entityManager.find(Course.class, id);
            oldCourse.setName(course.getName());
            oldCourse.setDescription(course.getDescription());
            oldCourse.setDescription(course.getDescription());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        System.out.println("updated Course");
    }

    @Override
    public void deleteCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
           Course findCourse = entityManager.find(Course.class, id);
            entityManager.remove(findCourse);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        System.out.println("deleted course");
    }

    @Override
    public Map<Course, List<Student>> getCourseStudents(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<Course,List<Student>> courseListMap = new HashMap<>();
        List<Student> students = new ArrayList<>();
        Course course = null;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class, id);
            students = course.getStudents();
            courseListMap.put(course,students);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
     return courseListMap;
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        Student student = null;

        try{
            entityManager.getTransaction().begin();

            course = entityManager.find(Course.class, courseId);
            student = entityManager.find(Student.class, studentId);

            if (course != null && student != null) {
                if(course.getStudents() == null) course.setStudents(new ArrayList<>());
                course.getStudents().add(student);
                if(student.getCourses() == null) student.setCourses(new ArrayList<>());
                student.getCourses().add(course);

                entityManager.merge(course);
                entityManager.merge(student);

            }else {
                System.out.println("Not found Course OR Student!");
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
