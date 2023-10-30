package net.kcww.app.common.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@MappedSuperclass
@Data
public class AbstractUserEntity extends AbstractEntity{

  @Version
  @Setter(AccessLevel.NONE)
  private int version;

}
