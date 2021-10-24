package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.cup.models.Match;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    void deleteMatchEntityById(Long id);

    Optional<Match> findMatchEntityById(Long id);
}