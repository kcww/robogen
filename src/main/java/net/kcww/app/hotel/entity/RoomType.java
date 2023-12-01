package net.kcww.app.hotel.entity;

import com.helger.commons.name.IHasName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "room_types")
public class RoomType extends AbstractEntity implements IHasName {

    @Column(nullable = false, length = 255)
    @NotBlank
    @Size(max = 255)
    private String name;

}