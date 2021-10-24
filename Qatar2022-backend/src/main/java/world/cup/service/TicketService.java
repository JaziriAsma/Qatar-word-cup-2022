package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.cup.models.Ticket;
import world.cup.repositories.MatchRepository;
import world.cup.repositories.TicketRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final MatchRepository matchRepository;


    @Autowired
    public TicketService(TicketRepository ticketRepository, MatchRepository matchRepository) {
        this.ticketRepository = ticketRepository;

        this.matchRepository = matchRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }


    public void deleteTicket(Long ticketId) {
        ticketRepository.findById(ticketId);
        boolean exists = ticketRepository.existsById(ticketId);
        if (!exists) {
            throw new IllegalStateException("Ticket with id " + ticketId + " does not exists");
        }
        ticketRepository.deleteById(ticketId);
    }



    public Ticket addTicket(Ticket ticket) {
        // Optional<MatchEntity> optionalMatchEntity = matchRepository.findMatchEntityById(ticket.getMatchEntity().getId());

        return ticketRepository.save(ticket);
    }


    public void updateTicket(Long id, Ticket ticketUpdate) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "ticket with id " + id + " does not exist"));
        if (ticketUpdate.getTicketNumber() != null  &&
                !Objects.equals(ticket.getTicketNumber(), ticketUpdate.getTicketNumber())) {
            ticket.setTicketNumber(ticketUpdate.getTicketNumber());
        }
        if (ticketUpdate.getTicketType() != null &&
                ticket.getTicketType().length() > 0 &&
                !Objects.equals(ticket.getTicketType(), ticketUpdate.getTicketType())) {
            ticket.setTicketType(ticketUpdate.getTicketType());
        }
        if (ticketUpdate.getPrice() != null &&
                !Objects.equals(ticket.getPrice(), ticketUpdate.getPrice())) {
            ticket.setPrice(ticketUpdate.getPrice());
        }
        if (ticketUpdate.getMatch() != null &&
                !Objects.equals(ticket.getMatch(), ticketUpdate.getMatch())) {
            ticket.setMatch(ticketUpdate.getMatch());
        }
        ticketRepository.save(ticket);
    }

}