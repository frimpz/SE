package frimpong.jpa.dataModel.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Added two unit test to this class
 * One to test for room addition, the other to test for telephone number validation.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExampleTest {

    private static final Logger logger = Logger.getLogger(ExampleTest.class.getName());

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testPerson(){
        populate();
        List<Person> persons = entityManager.getEntityManager()
                .createQuery(" from Person",Person.class).getResultList();

        ObjectMapper mapperObj = new ObjectMapper();
        String personJson =persons.get(0).getName();
        try {
            personJson = mapperObj.writeValueAsString(persons);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING,"Json cannot not be processed",e);
        }

        System.out.println(personJson);


    }



    @Test
    public void testEmailValidation(){
        Person person = new Person();
        person.setEmail("hahaWrongEmail");
        person.setName("John");
        person.setBirthDate(getDate("2017-11-15"));
        assertThatThrownBy(() -> { entityManager.persist(person); }).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("must contain valid email address");

    }

    @Test
    public void testPersonBirthDate(){
        populate();
        Person person = (Person) entityManager.getEntityManager().createQuery("SELECT p FROM Person p WHERE p.name LIKE 'Sheldon' ").getResultList().get(0);
        assertEquals(getDate("1991-11-05"),(person.getBirthDate()));
    }

    /**
     * This test method selects all teams in the given contest and prints them.
     *
     */
    @Test
    public void selectTeams(){
        populate();
        Contest subcontest = (Contest) entityManager.getEntityManager().createQuery("SELECT c FROM Contest c WHERE c.name LIKE 'Advanced Data Model' ").getResultList().get(0);
        System.out.println(subcontest.getTeams().size());
        for(Team t : subcontest.getTeams()){
            System.out.println("Team :"+ t.getName()+" "+t.getState()+" "+t.getRank());
        }
    }

    /**
     * This test method  provide a report
     * stating how many students there are grouped by age
     *
     */
    @Test
    public void generateReport(){
        populate();

        List<Team> teams = entityManager.getEntityManager()
                .createQuery("SELECT c FROM Team c ").getResultList();
        Set<Person> persons = new HashSet<>();
        for(Team t : teams){
             persons.addAll(t.getMembership());
        }

        Map<String,Set<Person>> ageGroup = new HashMap<>();

        for (Person p: persons){
            String age = getAge(p.getBirthDate());
            if(ageGroup.containsKey(age)){
                ageGroup.get(age).add(p);
            }else{
                Set<Person> personSet = new HashSet<>();
                personSet.add(p);
                ageGroup.put(age,personSet);
            }
        }

        for(String s : ageGroup.keySet()){
            System.out.print(s+"  ");
            for(Person p : ageGroup.get(s)){
                System.out.print(p.getName()+"  ");
            }
            System.out.println("  ");
        }
    }


    /**
     * This method is used to calculate the ages
     * @param birthDate
     * @return
     */
    private String getAge(Date birthDate) {
        Date today = new Date();
        int age = today.getYear() - birthDate.getYear();
        int m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m == 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }
        return String.valueOf(age);
    }

    /**
     * This test method  provide a report
     * stating how many students there are grouped by age
     *
     */
    @Test
    public void contestOccupancy(){
        populate();

        Contest contest = (Contest) entityManager.getEntityManager().createQuery("SELECT c FROM Contest c WHERE c.name LIKE 'Data Model' ").getResultList().get(0);
        int occupancy = contest.getTeams().size();
        int capacity = contest.getCapacity();
        assert(occupancy<=capacity);
    }

    //Actual method to populate the database.
    public void populate(){

        Contest contest = new Contest();
        contest.setName("Data Model");
        contest.setCapacity(10);
        contest.setDate(getDate("2020-01-15"));
        contest.setRegistrationAllowed(true);
        contest.setRegistrationFrom(getDate("2019-11-15"));
        contest.setRegistrationTo(getDate("2019-12-15"));

        Contest subContest = new Contest();
        subContest.setName("Advanced Data Model");
        subContest.setCapacity(5);
        subContest.setDate(getDate("2020-05-15"));
        subContest.setRegistrationAllowed(true);
        subContest.setRegistrationFrom(getDate("2020-03-15"));
        subContest.setRegistrationTo(getDate("2020-04-15"));



        // Populate teams
        Set<Team> teams = new HashSet<>();
        Team team = new Team();
        team.setName("Baylor");
        team.setRank(5);
        team.setState(team.stateAccepted());

        // Populate 3 Person in team Baylor
        Person coach = new Person();
        coach.setName("Sheldon");
        coach.setEmail("sheldor@email.com");
        coach.setBirthDate(getDate("1991-11-05"));
        coach.setUniversity("Baylor");
        team.setCoach(coach);
        entityManager.persist(coach);

        Person person = new Person();
        person.setName("John");
        person.setEmail("balor@email.com");
        person.setBirthDate(getDate("1993-11-01"));
        person.setUniversity("Baylor");
        entityManager.persist(person);

        Person person1 = new Person();
        person1.setName("Samuel");
        person1.setEmail("baylor@email.com");
        person1.setBirthDate(getDate("1996-11-25"));
        person1.setUniversity("Baylor");
        entityManager.persist(person1);

        Person person2 = new Person();
        person2.setName("Jackson");
        person2.setEmail("bylor@email.com");
        person2.setBirthDate(getDate("1996-11-05"));
        person2.setUniversity("Baylor");
        entityManager.persist(person2);

        Set<Person> persons = new HashSet<>();
        persons.add(person);
        persons.add(person1);
        persons.add(person2);

        //Adding persons to team
        team.setMembership(persons);
        entityManager.persist(team);
        teams.add(team);


        Team team1 = new Team();
        team1.setName("UCLA");
        team1.setRank(1);
        team1.setState(team1.statePending());

        // Populate 3 Person in team UCLA
        Person coach1 = new Person();
        coach1.setName("Lee");
        coach1.setEmail("lee@email.com");
        coach1.setBirthDate(getDate("1994-11-11"));
        coach1.setUniversity("UCLA");
        team1.setCoach(coach1);
        entityManager.persist(coach1);

        Person person3 = new Person();
        person3.setName("Galecki");
        person3.setEmail("ucla@email.com");
        person3.setBirthDate(getDate("1994-11-15"));
        person3.setUniversity("UCLA");
        entityManager.persist(person3);

        Person person4 = new Person();
        person4.setName("Rowan");
        person4.setEmail("ula@email.com");
        person4.setBirthDate(getDate("1991-11-15"));
        person4.setUniversity("UCLA");
        entityManager.persist(person4);

        Person person5 = new Person();
        person5.setName("Jonny");
        person5.setEmail("cla@email.com");
        person5.setBirthDate(getDate("1990-11-10"));
        person5.setUniversity("UCLA");
        entityManager.persist(person5);

        Set<Person> persons2 = new HashSet<>();
        persons2.add(person5);
        persons2.add(person4);
        persons2.add(person3);

        team1.setMembership(persons2);
        entityManager.persist(team1);
        teams.add(team1);






        Team team2 = new Team();
        team2.setName("UT Arlington");
        team2.setRank(1);
        team2.setState(team2.statePending());


        Person coach2 = new Person();
        coach2.setName("Cooper");
        coach2.setEmail("cooper@email.com");
        coach2.setBirthDate(getDate("1995-11-17"));
        coach2.setUniversity("ut Arlinton");
        team2.setCoach(coach2);
        entityManager.persist(coach2);

        Person person6 = new Person();
        person6.setName("Pearl");
        person6.setEmail("lington@email.com");
        person6.setBirthDate(getDate("1994-11-15"));
        person6.setUniversity("Arlington");
        entityManager.persist(person6);

        Person person7 = new Person();
        person7.setName("Rowan");
        person7.setEmail("arlton@email.com");
        person7.setBirthDate(getDate("1995-11-15"));
        person7.setUniversity("Arlington");
        entityManager.persist(person7);

        Person person8 = new Person();
        person8.setName("Jonny");
        person8.setEmail("arl@email.com");
        person8.setBirthDate(getDate("1995-11-19"));
        person8.setUniversity("Arlington");
        entityManager.persist(person8);

        Set<Person> persons3 = new HashSet<>();
        persons3.add(person6);
        persons3.add(person7);
        persons3.add(person8);

        team2.setMembership(persons3);
        entityManager.persist(team2);
        teams.add(team2);


        subContest.setTeams(teams);
        entityManager.persist(subContest);

        contest.setSubContest(subContest);

        Person manager = new Person();
        manager.setName("Apuzu");
        manager.setEmail("aps@email.com");
        manager.setBirthDate(getDate("1990-11-15"));
        manager.setUniversity("Baylor");
        entityManager.persist(manager);

        Set<Person> persons4 = new HashSet<>();
        persons4.add(manager);
        contest.setManagers(persons4);

        entityManager.persist(contest);

    }

    //this method is used to convert a string to date format.
    private Date getDate(String s) {
        Date date = null;
        try {
            date =  new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            logger.log(Level.WARNING,"Date cannot be created fro string",e);
        };
        return date;
    }


}
