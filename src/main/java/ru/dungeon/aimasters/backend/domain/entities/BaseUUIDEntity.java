package ru.dungeon.aimasters.backend.domain.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Абстрактный класс, являющийся базовым для всех сущностей с UUID идентификатором
 *
 * @author Ermakov KS
 * @since 4.04.2023
 */
@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseUUIDEntity {

    /**
     * Уникальный идентификатор сущности в формате uuid, генерируемый автоматически при создании
     */
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;
}
