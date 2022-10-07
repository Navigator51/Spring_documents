package su.goodcat.spring_documents.domain.dto;

import lombok.Value;
import su.goodcat.spring.domain.docproject.FrontStatus;


public record DocumentRequestDTO(FrontStatus status) {

}
