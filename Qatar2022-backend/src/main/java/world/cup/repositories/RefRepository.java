package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Ref;

import java.util.Optional;

@Repository
public interface RefRepository  extends JpaRepository<Ref,Long> {
    Optional<Ref> findRefById(Long id);

    void deleteRefById(Long id);
}
