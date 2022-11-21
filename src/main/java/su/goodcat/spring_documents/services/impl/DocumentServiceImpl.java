package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.goodcat.commonlib.domain.*;
import su.goodcat.spring_documents.domain.Document;
import su.goodcat.spring_documents.mappers.DocumentMapper;
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

    @Override
    @Transactional(readOnly = true)
    public ResponseDTOWithDTOList getCounterPartyNotReadDocuments(CounterpartyDocumentRequestDTO counterpartyDocumentRequestDTO) {
        //меняем местами отправителя и получателя в зависимости от того, входящие или исходящие документы нужны
        long senderId = 0;
        long recipientId = 0;
        if (counterpartyDocumentRequestDTO.getFrontStatus().equals(FrontStatus.INCOMING)) {
            senderId = counterpartyDocumentRequestDTO.getCounterpartyID();
            recipientId = counterpartyDocumentRequestDTO.getUserId();
        } else if (counterpartyDocumentRequestDTO.getFrontStatus().equals(FrontStatus.OUTCOMING)) {
            senderId = counterpartyDocumentRequestDTO.getUserId();
            recipientId = counterpartyDocumentRequestDTO.getCounterpartyID();
        }
        List<Long> idList = documentRepository.getCounterpartyDocument
                (senderId, recipientId, counterpartyDocumentRequestDTO.getForbiddenStatus());
        List<Document> documentList = documentRepository.findByAllDependensies(idList);
        return new ResponseDTOWithDTOList(Mappers.getMapper(DocumentMapper.class).fromDocumentToDocumentDTO(documentList));

    }
}
