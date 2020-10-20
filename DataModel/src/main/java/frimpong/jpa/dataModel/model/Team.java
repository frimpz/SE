package frimpong.jpa.dataModel.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Team entity class
 */

@Entity
public class Team {

    private enum State {
        Accepted, Pending, Canceled;
    }

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String name;


    @Column
    private int rank;

    @Column
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "fk_Contest")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Contest contest;


    @ManyToMany
    @JoinTable(name = "person_team",
            joinColumns = { @JoinColumn(name = "Team_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "Person_ID", referencedColumnName = "ID") })
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Person> membership = new HashSet<>();



    @ManyToOne
    @JoinColumn(name = "fk__Person")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Person coach;


    @OneToOne
    @JoinColumn(name = "fk_team")
    private Team subTeam;


    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }


    public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }


    public Team getSubTeam() {
        return subTeam;
    }

    public void setSubTeam(Team subTeam) {
        this.subTeam = subTeam;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public State stateAccepted(){
        return State.Accepted;
    }

    public State statePending(){
        return State.Accepted;
    }

    public State stateCanceled(){
        return State.Accepted;
    }

    public Set<Person> getMembership() {
        return membership;
    }

    public void setMembership(Set<Person> membership) {
        this.membership = membership;
    }
}
