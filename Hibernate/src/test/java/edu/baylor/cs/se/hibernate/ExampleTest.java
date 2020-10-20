package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.model.Course;
import edu.baylor.cs.se.hibernate.model.Room;
import edu.baylor.cs.se.hibernate.model.Student;
import edu.baylor.cs.se.hibernate.model.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Added two unit test to this class
 * One to test for room addition, the other to test for telephone number validation.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExampleTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    //simple test
    public void demoTest(){
        Teacher teacher = new Teacher();
        teacher.setEmail("email@email.com");
        teacher.setFirstName("John");
        teacher.setLastName("Roe");
        teacher.setTelephone("1000000000");
        entityManager.persist(teacher);
        Teacher dbTeacher = (Teacher)entityManager.getEntityManager().createQuery("SELECT t FROM Teacher t WHERE t.firstName LIKE 'John' ").getResultList().get(0);
        assertThat(teacher.getFirstName()).isEqualToIgnoringCase(dbTeacher.getFirstName());
    }

    @Test
    //tests that email validation works
    public void anotherDemoTest(){
        Teacher teacher = new Teacher();
        teacher.setEmail("hahaWrongEmail");
        teacher.setFirstName("John");
        teacher.setTelephone("1000000000");
        teacher.setLastName("Roe");
        assertThatThrownBy(() -> { entityManager.persist(teacher); }).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("must contain valid email address");
    }


    @Test
    //tests that telephone number validation works
    public void telephoneTest(){
        Teacher teacher = new Teacher();
        teacher.setEmail("email@email.com");
        teacher.setFirstName("John");
        teacher.setTelephone("00");
        teacher.setLastName("Roe");
        assertThatThrownBy(() -> { entityManager.persist(teacher); }).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("Telephone must be at least 10 digits");
    }


    @Test
    //tests that room Addition works
    public void RoomTest(){
        Room room= new Room();
        room.setName("Room 101");
        entityManager.persist(room);

        Student student = new Student();
        student.setName("Frimpong");
        entityManager.persist(student);

        Teacher teacher = new Teacher();
        teacher.setEmail("email@email.com");
        teacher.setFirstName("John");
        teacher.setTelephone("1000000000");
        teacher.setLastName("Roe");
        entityManager.persist(teacher);

        Course course = new Course();
        course.setName("Software engineering");
        course.setTeacher(teacher);
        course.getStudents().add(student);
        course.setRoom(room);
        entityManager.persist(course);

        Course dbCourse= (Course)entityManager.getEntityManager().createQuery("SELECT c FROM Course  c WHERE c.name LIKE 'Software engineering' ").getResultList().get(0);
        assertThat(course.getRoom().getName()).isEqualToIgnoringCase(dbCourse.getRoom().getName());
    }


}
