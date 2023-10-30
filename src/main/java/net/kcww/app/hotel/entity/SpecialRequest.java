package net.kcww.app.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
//@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "special_requests")
public class SpecialRequest extends AbstractEntity {

    private Boolean lateCheckout;
    private Boolean earlyCheckin;
    private Boolean roomUpgrade;

    @Column(length = 255)
    @Size(max = 255)
    private String others;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}