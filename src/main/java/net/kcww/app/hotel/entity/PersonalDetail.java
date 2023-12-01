package net.kcww.app.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "personal_details")
public class PersonalDetail extends AbstractEntity {

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Column(nullable = false, length = 100)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(nullable = false, length = 20)
    @NotBlank
    @Size(max = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}