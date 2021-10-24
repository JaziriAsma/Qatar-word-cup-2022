package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.cup.models.Match;
import world.cup.models.Ref;
import world.cup.repositories.MatchRepository;
import world.cup.repositories.RefRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class RefService {
    private final MatchRepository matchRepository;
    private final RefRepository refRepository;

    @Autowired
    public RefService(MatchRepository matchRepository, RefRepository refRepository) {
        this.matchRepository = matchRepository;
        this.refRepository = refRepository;
    }

    public Ref addRef(Ref ref) {
        return refRepository.save(ref);
    }

    public List<Ref> findRefs() {
        return refRepository.findAll();
    }

    public Ref findRefById(Long id) {
        return refRepository.findRefById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "ref with id " + id + " does not exist"));

    }

    public void deleteRef(Long id) {
        refRepository.deleteRefById(id);
    }

    public Ref updateRef(Long id, Ref refUpdate) {
        Ref ref = refRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "ref with id " + id + " does not exist"));

        if (ref.getName() != null &&
                ref.getName().length() > 0 &&
                !Objects.equals(ref.getName(), refUpdate.getName())) {
            ref.setName(refUpdate.getName());
        }
        if (ref.getLastname() != null &&
                ref.getLastname().length() > 0 &&
                !Objects.equals(ref.getLastname(), refUpdate.getLastname())) {
            ref.setLastname(refUpdate.getLastname());
        }
        if (ref.getDob() != null &&
                ref.getDob().toString().length() > 0 &&
                !Objects.equals(ref.getDob(), refUpdate.getDob())) {
            ref.setDob(refUpdate.getDob());
        }
        if (ref.getNationality() != null &&
                ref.getNationality().length() > 0 &&
                !Objects.equals(ref.getNationality(), refUpdate.getNationality())) {
            ref.setNationality(refUpdate.getNationality());
        }
        if (ref.getSalary() != null &&
                ref.getSalary().toString().length() > 0 &&
                !Objects.equals(ref.getSalary(), refUpdate.getSalary())) {
            ref.setSalary(refUpdate.getSalary());
        }
        if (ref.getImage() != null &&
                ref.getImage().length() > 0 &&
                !Objects.equals(ref.getImage(), refUpdate.getImage())) {
            ref.setImage(refUpdate.getImage());
        }

        refRepository.save(ref);
        return ref;
    }

    @Transactional
    public void linkRefToMatch(Long refId, Long matchId) {
        Ref ref = refRepository.findById(refId).orElseThrow(()-> new IllegalStateException(
                "ref with id " + refId + " does not exist"));
        Match match = matchRepository.findById(matchId).orElseThrow(()-> new IllegalStateException(
                "match with id " + matchId + " does not exist"));
        ref.setMatch(match);
        refRepository.save(ref);

        Set<Ref> refs = match.getRefs();
        refs.add(ref);
        match.setRefs(refs);
        matchRepository.save(match);
    }
}
