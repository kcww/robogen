package net.kcww.app.robogen.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kcww.app.common.entity.AbstractEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "input_files")
public class InputFile extends AbstractEntity {

  public static final int MAX_FILE_NAME_LENGTH = 255;

  private Long userId;

  @Column(nullable = false, length = MAX_FILE_NAME_LENGTH)
  private String featureFileName;

  @Column(nullable = false, length = MAX_FILE_NAME_LENGTH)
  private String xmlFileName;

  @Column(length = MAX_FILE_NAME_LENGTH)
  private String xsdFileName;

  @Lob
  private byte[] featureFile;

  @Lob
  private byte[] xmlFile;

  @Lob
  private byte[] xsdFile;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime uploadedAt;

}
