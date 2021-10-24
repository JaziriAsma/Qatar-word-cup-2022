package world.cup.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.ERole;
import world.cup.models.Role;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findRoleByName(ERole name);
}
