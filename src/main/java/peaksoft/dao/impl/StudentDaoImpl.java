package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.connection.DatabaseConnection;
import peaksoft.dao.StudentDao;
import peaksoft.entity.Course;
import peaksoft.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao { EntityManagerFactory entityManagerFactory = DatabaseConnection.entityManagerFactory();

    @Override
    public void save(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        System.out.println("saved student");
    }

    @Override
    public Student getStudentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;

        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return student;
    }

    @Override
    public void updateStudentName(Long id, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
          Student  student = entityManager.find(Student.class, id);
            if (student != null) {
                student.setFullName(name);
                entityManager.merge(student);
            } else {
                System.out.println("Not found student with id : "+id);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        System.out.println(" updated");
    }

    @Override
    public void deleteStudent(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Student student = null;
        try {
            entityManager.getTransaction().begin();

            student = entityManager.find(Student.class, id);
            System.out.println("not found this id");
            for (Course cours : student.getCourses()) {
                student.getCourses().remove(cours);
            }
            entityManager.remove(student);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

        System.out.println("deleted student");
    }

    @Override
    public List<Course> getStudentCourses(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;
        List<Course>  courses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();

            student = entityManager.find(Student.class, id);
            courses = student.getCourses();

            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return courses;
    }

    @Override
    public List<Student> getStudentsByRecentEnrollments(int dateLimit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List students = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            students = entityManager.createQuery("select s from Student s order by s.enrollmantDate desc ").setMaxResults(dateLimit)
                    .getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return students;
    }
}
