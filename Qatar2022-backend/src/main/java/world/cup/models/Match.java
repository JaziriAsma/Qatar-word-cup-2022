package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Match {

    @Id
    @SequenceGenerator(
            name = "match_sequence",
            sequenceName = "match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    private String teamA;
    private String imageUrlA;
    private String teamB;
    private String imageUrlB;
    private LocalDate matchDate;
    private LocalTime beginningTime;
    private String matchType;
    private String matchLocation;



    @JsonIgnore
    @OneToMany(mappedBy="match",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @JsonIgnore
    @OneToMany(mappedBy="match",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Ref> refs;

    public String getMatchLocation() {
        return matchLocation;
    }

    public void setMatchLocation(String matchLocation) {
        this.matchLocation = matchLocation;
    }

    public Match(){}


    public Match(Long id, String teamA, String imageUrlA, String teamB, String imageUrlB, LocalDate matchDate, LocalTime beginningTime, String matchType, String matchLocation, Set<Ticket> tickets) {
        this.id = id;
        this.teamA = teamA;
        this.imageUrlA = imageUrlA;
        this.teamB = teamB;
        this.imageUrlB = imageUrlB;
        this.matchDate = matchDate;
        this.beginningTime = beginningTime;
        this.matchType = matchType;
        this.matchLocation = matchLocation;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getImageUrlA() {
        return imageUrlA;
    }

    public void setImageUrlA(String imageUrlA) {
        this.imageUrlA = imageUrlA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getImageUrlB() {
        return imageUrlB;
    }

    public void setImageUrlB(String imageUrlB) {
        this.imageUrlB = imageUrlB;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(LocalTime beginningTime) {
        this.beginningTime = beginningTime;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Ref> getRefs() {
        return refs;
    }

    public void setRefs(Set<Ref> refs) {
        this.refs = refs;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", teamA='" + teamA + '\'' +
                ", imageUrlA='" + imageUrlA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", imageUrlB='" + imageUrlB + '\'' +
                ", matchDate=" + matchDate +
                ", beginningTime=" + beginningTime +
                ", matchType='" + matchType + '\'' +
                ", matchLocation='" + matchLocation + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}