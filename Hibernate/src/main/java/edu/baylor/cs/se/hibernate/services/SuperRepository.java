package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.Course;
import edu.baylor.cs.se.hibernate.model.Room;
import edu.baylor.cs.se.hibernate.model.Student;
import edu.baylor.cs.se.hibernate.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Spring annotations, feel free to ignore it
@Repository
@Transactional
public class SuperRepository {

    @PersistenceContext
    private EntityManager em;

    public void populate(){
        Student student = createStudent("Joe");
        Student student2 = createStudent("John");
        Student student3 = createStudent("Bob");
        Student student4 = createStudent("Tim");
        Student student5 = createStudent("Jimmy");


        Teacher teacher = new Teacher();
        teacher.setFirstName("Bob");
        teacher.setLastName("Porter");
        teacher.setEmail("bob@porter.com");
        teacher.setTelephone("1000000000");
        em.persist(teacher);

        Room room1= new Room();
        room1.setName("Room 101");
        em.persist(room1);

        Room room2 = new Room();
        room2.setName("Room 102");
        em.persist(room2);

        Course course = new Course();
        course.setName("Software engineering");
        course.setTeacher(teacher);
        course.getStudents().add(student);
        course.getStudents().add(student3);
        course.getStudents().add(student4);
        course.setRoom(room1);
        em.persist(course);

        Course course2 = new Course();
        course2.setName("Boring class");
        course2.setTeacher(teacher);
        course2.getStudents().add(student);
        course2.getStudents().add(student5);
        course2.setRoom(room2);
        em.persist(course2);



        //Do you know why this is not working?
        student2.getCourses().add(course);
        em.persist(student2);

    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<Course> getCoursesBySize(){
        return em.createQuery("SELECT c FROM Course c WHERE c.students.size > 2 ").getResultList();
    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<Course> getCoursesByStudentName(){
        return em.createNamedQuery("Course.findCoursesByStudentName").setParameter("name","Jimmy").getResultList();
    }

    public List<Teacher> getTeachers(){
        return em.createQuery(" from Teacher",Teacher.class).getResultList();
    }

    public List<Room> getRooms(){
        return em.createQuery(" from Room",Room.class).getResultList();
    }

    private Student createStudent(String name){
        Student student = new Student();
        student.setName(name);
        em.persist(student);
        return student;
    }
}
