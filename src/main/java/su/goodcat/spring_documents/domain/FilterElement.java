package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;



@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class FilterElement   {
  @JsonProperty("type")
  private ElementType type = ElementType.UNIT;
}