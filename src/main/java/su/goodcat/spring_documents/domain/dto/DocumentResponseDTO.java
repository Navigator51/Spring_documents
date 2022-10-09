package su.goodcat.spring_documents.domain.dto;

import su.goodcat.spring_documents.domain.Status;

//создание ДТО через record
public record DocumentResponseDTO(Long id, String senderUserName, int countFiles, Status status) {
}
