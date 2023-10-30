package net.kcww.app.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "application_user")
@Getter
@Setter
public class User extends AbstractUserEntity {

  private String username;
  private String name;

  @JsonIgnore
  private String hashedPassword;

  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<Role> roles;

  @Lob
  @Column(length = 1000000)
  private byte[] profilePicture;

}