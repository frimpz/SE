package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

//Obsolete with Java 8 @Repeatable annotation
@NamedQueries({
        @NamedQuery(
                name = "Course.findCoursesByStudentName",
                query = "SELECT c FROM Course c JOIN c.students s WHERE s.name = :name"
        )
})
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Teacher teacher;

    @ManyToMany//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Student> students = new HashSet();


    @ManyToOne
    //annotation bellow is just for Jackson serialization in controller
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Room room;

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

}
