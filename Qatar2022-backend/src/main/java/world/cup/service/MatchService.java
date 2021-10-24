package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.cup.models.Match;
import world.cup.models.Ticket;
import world.cup.repositories.MatchRepository;
import world.cup.repositories.TicketRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TicketRepository ticketRepository;


    @Autowired
    public MatchService(MatchRepository matchRepository, TicketRepository ticketRepository) {
        this.matchRepository = matchRepository;
        this.ticketRepository = ticketRepository;
    }

    public Match addMatchEntity(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> findAllMatchEntity() {
        return matchRepository.findAll();
    }


    public Match findMatchEntityById(Long id) {
        return matchRepository.findMatchEntityById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "match with id " + id + " does not exist"));

    }

    public void deleteMatchEntity(Long id) {
        matchRepository.deleteMatchEntityById(id);
    }


    public void updateMatchEntity(Long id, Match matchUpdate) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "match with id " + id + " does not exist"));

        if (matchUpdate.getTeamA() != null &&
                match.getTeamA().length() > 0 &&
                !Objects.equals(match.getTeamA(), matchUpdate.getTeamA())) {
            match.setTeamA(matchUpdate.getTeamA());
        }
        if (matchUpdate.getTeamB() != null &&
                match.getTeamB().length() > 0 &&
                !Objects.equals(match.getTeamB(), matchUpdate.getTeamB())) {
            match.setTeamB(matchUpdate.getTeamB());
        }
        if (matchUpdate.getImageUrlA() != null &&
                match.getImageUrlA().length() > 0 &&
                !Objects.equals(match.getImageUrlA(), matchUpdate.getImageUrlA())) {
            match.setImageUrlA(matchUpdate.getImageUrlA());
        }
        if (matchUpdate.getImageUrlB() != null &&
                match.getImageUrlB().length() > 0 &&
                !Objects.equals(match.getImageUrlB(), matchUpdate.getImageUrlB())) {
            match.setImageUrlB(matchUpdate.getImageUrlB());
        }
        if (matchUpdate.getBeginningTime() != null &&
                !Objects.equals(match.getBeginningTime(), matchUpdate.getBeginningTime())) {
            match.setBeginningTime(matchUpdate.getBeginningTime());
        }
        if (matchUpdate.getMatchType() != null &&
                !Objects.equals(match.getMatchType(), matchUpdate.getMatchType())) {
            match.setMatchType(matchUpdate.getMatchType());
        }

        if (matchUpdate.getMatchLocation() != null &&
                !Objects.equals(match.getMatchLocation(), matchUpdate.getMatchLocation())) {
            match.setMatchLocation(matchUpdate.getMatchLocation());
        }
        if (matchUpdate.getTickets() != null &&
                !Objects.equals(match.getTickets(), matchUpdate.getTickets())) {
            match.setTickets(matchUpdate.getTickets());
        }
        matchRepository.save(match);
    }



    public void linkNewTicketToMatch(Long ticketId, Long matchId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new IllegalStateException(
                "ticket with id " + ticketId + " does not exist"));
        Match match = matchRepository.findById(matchId).orElseThrow(()-> new IllegalStateException(
                "match with id " + matchId + " does not exist"));
        ticket.setMatch(match);
        ticketRepository.save(ticket);

        Set<Ticket> tickets = match.getTickets();
        tickets.add(ticket);
        match.setTickets(tickets);
        matchRepository.save(match);
    }
}