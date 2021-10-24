package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.cup.models.Ref;
import world.cup.service.RefService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ref")
public class RefController {
    private final RefService refService;

    @Autowired
    public RefController(RefService refService) {
        this.refService = refService;
    }

    @GetMapping
    public ResponseEntity<List<Ref>> getRefs() {
        List<Ref> refs = refService.findRefs();
        return new ResponseEntity<>(refs, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ref> getRef(@PathVariable("id") Long id) {
        Ref ref = refService.findRefById(id);
        return new ResponseEntity<>(ref, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ref> addRef(@RequestBody Ref ref) {
        return new ResponseEntity<>(refService.addRef(ref), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRef(@PathVariable("id") Long id) {
        refService.deleteRef(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path="{id}")
    public void updateRef(
            @PathVariable("id") Long id,
            @RequestBody Ref refUpdate
    ){
        refService.updateRef(id,refUpdate);
    }

    @GetMapping(path="/link/{refId}/{matchId}")
    public void linkRefToMatch(@PathVariable("refId") Long refId, @PathVariable("matchId") Long matchId){
        refService.linkRefToMatch(refId, matchId);
    }

}
