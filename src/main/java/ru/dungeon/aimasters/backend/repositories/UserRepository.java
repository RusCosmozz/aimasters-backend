package ru.dungeon.aimasters.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import ru.dungeon.aimasters.backend.domain.entities.User;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Repository
public interface UserRepository extends BaseUUIDRepository<User> {

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);

}
