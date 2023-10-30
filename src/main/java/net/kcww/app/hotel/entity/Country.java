package net.kcww.app.hotel.entity;

import com.helger.commons.name.IHasName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;

@Data
//@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends AbstractEntity implements IHasName {

    @Column(unique = true, length = 2)
    @Size(min = 2, max = 2)
    private String isoCode;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String name;

}