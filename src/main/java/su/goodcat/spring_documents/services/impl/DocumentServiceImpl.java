package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import su.goodcat.spring_documents.domain.Document;
import su.goodcat.spring_documents.domain.Status;
import su.goodcat.spring_documents.domain.dto.DocumentRequestDTO;
import su.goodcat.spring_documents.domain.dto.DocumentResponseDTO;
import su.goodcat.spring_documents.domain.mappers.DocumentMapper;
import su.goodcat.spring_documents.repositories.DocumentRepository;
import su.goodcat.spring_documents.services.interfaces.DocumentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public List<DocumentResponseDTO> getDocumentListByStatus(DocumentRequestDTO documentRequestDTO) {
        List<Status> statusList = documentRequestDTO.status().getStatusList();
        List<Document> documentList = documentRepository.getDocumentsByStatus(statusList);
        return Mappers.getMapper(DocumentMapper.class).fromDocumentToDocumentResponseDTO(documentList);

    }
}
