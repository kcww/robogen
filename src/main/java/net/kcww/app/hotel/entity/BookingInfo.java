package net.kcww.app.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
//@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class BookingInfo extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private PersonalDetail user;

    @Column(nullable = false)
    @NotNull
    private LocalDate checkinDate;

    @Column(nullable = false)
    @NotNull
    private LocalDate checkoutDate;

    @NotNull()
    @Min(value = 1)
    @Max(value = 100)
    private Integer adults;

    @Min(value = 0)
    @Max(value = 100)
    private Integer children;

    @Column(name="smoking_preference", nullable = false)
    @NotNull
    private Boolean smokingPref;

    @ManyToOne
    @JoinColumn(nullable = false)
    private RoomType roomType;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}