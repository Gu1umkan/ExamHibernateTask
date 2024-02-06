package peaksoft.connection;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entity.Course;
import peaksoft.entity.Student;

import java.util.Properties;

public class DatabaseConnection {
    public static EntityManagerFactory entityManagerFactory(){
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_DRIVER,"org.postgresql.Driver");
        properties.put(Environment.JAKARTA_JDBC_URL,"jdbc:postgresql://localhost:5432/my_database");
        properties.put(Environment.JAKARTA_JDBC_USER,"postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD,"1234");
        properties.put(Environment.HBM2DDL_AUTO,"update");
        properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL,"true");
        properties.put(Environment.FORMAT_SQL,"true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Student.class);

        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
