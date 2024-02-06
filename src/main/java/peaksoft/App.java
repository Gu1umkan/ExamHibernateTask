package peaksoft;

import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.service.CourseService;
import peaksoft.service.StudentService;
import peaksoft.service.impl.CourseServiceImpl;
import peaksoft.service.impl.StudentServiceImpl;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        StudentService studentService = new StudentServiceImpl();
        CourseService courseService = new CourseServiceImpl();
   // studentService.save(new Student("Kanymai", "kanym@gmail.com", LocalDate.of(2021,1,12)));
//        System.out.println(studentService.getStudentById(1L));
//        studentService.updateStudentName(1L, "Zhigit Turumbekov");

//        studentService.deleteStudent(1L);

  //      System.out.println(studentService.getStudentsByRecentEnrollments(2));


//        courseService.save(new Course("kotlin", "android"));
//        courseService.save(new Course("c++", "back-end"));
//        courseService.save(new Course("python", "back"));
//        courseService.save(new Course("js", "front"));
//        courseService.save(new Course("java", "full"));
//
//        courseService.assignStudentToCourse(3L, 1L);
//        courseService.assignStudentToCourse(7L, 2L);
//        courseService.assignStudentToCourse(8L, 3L);


 //   System.out.println(courseService.getCourseById(4L));

       // courseService.deleteCourseById(3L);


        studentService.deleteStudent(6L);



    }
}
