package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@lombok.experimental.SuperBuilder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

public class GetDictionaryRequest {
  @JsonProperty("id")
  @Valid
  private List<Id> id = null;

  @JsonProperty("date")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @JsonProperty("includes")
  @Valid
  private List<String> includes = null;

  @JsonProperty("fields")
  @Valid
  private List<String> fields = null;

  @JsonProperty("search")
  @Valid
  private List<FilterElement> search = null;

  public GetDictionaryRequest addIdItem(Id idItem) {
    if (this.id == null) {
      this.id = new ArrayList<>();
    }
    this.id.add(idItem);
    return this;
  }
}