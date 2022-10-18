package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class DictionaryRecordsSearch   {
  @JsonProperty("content")
  @Valid
  private List<DictionaryRecord> content = null;

  @JsonProperty("totalElements")
  private Integer totalElements;


  }