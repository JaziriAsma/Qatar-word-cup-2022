package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import world.cup.models.Spectator;
import world.cup.models.User;
import world.cup.service.SpectatorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/spectator")
public class SpectatorController {
    private final SpectatorService spectatorService;

    @Autowired
    public SpectatorController(SpectatorService spectatorService){
        this.spectatorService=spectatorService;
    }

    @GetMapping
    public List<Spectator> getSpectators(){
        return spectatorService.getSpectators();
    }

    @GetMapping(path = "{spectatorId}")
    User getSpectator(@PathVariable Long spectatorId) {
        return spectatorService.getSpectator(spectatorId);
    }

    @PostMapping
    public Spectator registerNewSpectator(@Valid @RequestBody Spectator spectator, BindingResult bindingResult){
        return spectatorService.addNewSpectator(spectator,bindingResult);
    }

    @PutMapping(path="{userId}")
    public void updateSpectator(
            @PathVariable("userId") Long spectatorId,
            @RequestBody Spectator spectatorUpdate
    ){
        spectatorService.updateSpectator(spectatorId, spectatorUpdate);

    }

    @DeleteMapping("{id}")
    public void DeleteSpectatorById(@PathVariable Long id) {
        spectatorService.DeleteSpectator(id);
    }

    @PostMapping(path="/link/{spectatorId}/{ticketId}")
    public void linkSpectatorToTicket(@PathVariable("ticketId") Long ticketId, @PathVariable("spectatorId") Long spectatorId){
        spectatorService.linkNewSpectatorToTicket(spectatorId, ticketId);
    }
}

