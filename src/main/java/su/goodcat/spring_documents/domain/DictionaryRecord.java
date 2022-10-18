package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class DictionaryRecord   {
  @JsonProperty("data")
  private Object data;

  @JsonProperty("meta")
  private Object meta;
}