package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class DictionaryRecord   {
  @JsonProperty("data")
  @Setter
  @Getter
  private Object data;

  @JsonProperty("meta")
  private Object meta;
}