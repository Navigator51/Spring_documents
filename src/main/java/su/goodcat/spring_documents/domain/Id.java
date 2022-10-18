package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class Id   {
  @JsonProperty("guid")
  private UUID guid;

  @JsonProperty("version")
  private Integer version;
}