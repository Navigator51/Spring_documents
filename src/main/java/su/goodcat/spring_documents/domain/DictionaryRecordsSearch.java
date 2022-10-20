package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class DictionaryRecordsSearch   {
  @JsonProperty("content")
  @Valid
  @Getter
  @Setter
  private List<DictionaryRecord> content = null;

  @JsonProperty("totalElements")
  private Integer totalElements;

  public DictionaryRecordsSearch addContentItem(DictionaryRecord contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }
  }