package net.kcww.app.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
//@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking_confirmations")
public class BookingConfirmation extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private PersonalDetail user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private BookingInfo bookingInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private SpecialRequest specialRequest;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}