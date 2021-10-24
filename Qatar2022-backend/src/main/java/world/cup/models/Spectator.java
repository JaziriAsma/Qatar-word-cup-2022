package world.cup.models;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Spectator extends User {
    @NotBlank
    @NotNull(message = "Card number cannot be null")
    @Size(min = 16, max = 16, message = "Card number should contain 16 digits")
    private String cardNumber;

    @NotNull(message = "Expiration cannot be null")
    @Future
    private LocalDate expiration;

    @NotBlank
    @NotNull(message = "cvv cannot be null")
    @Size(min = 3, max = 3, message = "cvv should contain 3 digits")
    private String cvv;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;


    public Spectator() {
    }

    public Spectator(String firstName, String lastName, String email, String country, String tel, LocalDate dob, String password, String username, String image, String cardNumber, LocalDate expiration, String cvv) {
        super(firstName, lastName, email, country, tel, dob, password, username, image);
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
    }

    public Spectator(Long id, String firstName, String lastName, String email, String country, String tel, LocalDate dob, String password,String username, String image, String cardNumber, LocalDate expiration, String cvv) {
        super(id, firstName, lastName, email, country, tel, dob, password, username, image);
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "SpectatorEntity{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiration=" + expiration +
                ", cvv='" + cvv + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
