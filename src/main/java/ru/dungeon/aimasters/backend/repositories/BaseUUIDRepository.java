package ru.dungeon.aimasters.backend.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dungeon.aimasters.backend.domain.entities.BaseUUIDEntity;

public interface BaseUUIDRepository<T extends BaseUUIDEntity> extends JpaRepository<T, UUID> {

}
