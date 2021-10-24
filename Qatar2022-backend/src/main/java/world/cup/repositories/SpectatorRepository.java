package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Spectator;

import java.util.Optional;

@Repository
public interface SpectatorRepository extends JpaRepository<Spectator, Long> {
    Optional<Spectator> findSpectatorByEmail(String email) ;

}
