import entities.Student;
import entities.Teachers;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

       Student student = new Student("Jivko",23);
       Student secondStudent = new Student("Vesi",22);

       entityManager.persist(student);
       entityManager.persist(secondStudent);

        Student student1 = entityManager.find(Student.class, 1);
        entityManager.remove(student1);

        Teachers teacher = new Teachers("Iliqna", LocalDate.now());

        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
