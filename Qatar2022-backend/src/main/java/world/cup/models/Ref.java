package world.cup.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Ref {
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

    private String name;
    private String lastname;
    private LocalDate dob;
    private Float salary;
    private String nationality;
    @Lob
    private String image;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="match_id")
    Match match;

    public Ref() {
    }

    public Ref(Long id, String name, String lastname, LocalDate dob, Float salary, String nationality, String image, Match match) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dob = dob;
        this.salary = salary;
        this.nationality = nationality;
        this.image = image;
        this.match = match;
    }

    public Ref(String name, String lastname, LocalDate dob, Float salary, String nationality, String image, Match match) {
        this.name = name;
        this.lastname = lastname;
        this.dob = dob;
        this.salary = salary;
        this.nationality = nationality;
        this.image = image;
        this.match = match;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
