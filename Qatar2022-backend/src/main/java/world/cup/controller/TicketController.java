package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import world.cup.models.Ticket;
import world.cup.service.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }


    @GetMapping("/all")
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }


    @PostMapping("/add")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        Ticket newticket = ticketService.addTicket(ticket);
        return new ResponseEntity<>(newticket, HttpStatus.CREATED);
    }

    @DeleteMapping(path="{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Long ticketId){
        ticketService.deleteTicket(ticketId);
    }

    @PutMapping(path="{id}")
    public void updateTicket(
            @PathVariable("id") Long id,
            @RequestBody Ticket ticketUpdate
    ){
        ticketService.updateTicket(id,ticketUpdate);
    }


}