package peaksoft.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "student_seq", allocationSize = 1)
public class Student extends BaseEntity{
  private String fullName;
  @Column(unique = true)
  private String email;
  @Column(nullable = false)
  private LocalDate enrollmantDate;
  @ManyToMany(mappedBy = "students")
  private List<Course> courses;

  public Student(String fullName, String email, LocalDate enrollmantDate) {

    this.fullName = fullName;
    this.email = email;
    this.enrollmantDate = enrollmantDate;
  }

  @Override
  public String toString() {
    return "Student{" +
            "fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", enrollmantDate=" + enrollmantDate +
            '}';
  }
}
