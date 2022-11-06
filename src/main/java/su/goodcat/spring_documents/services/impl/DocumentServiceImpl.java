package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import su.goodcat.commonlib.domain.*;
import su.goodcat.commonlib.feign.UserFeignClient;
import su.goodcat.spring_documents.constants.ErrorMessagesConstants;
import su.goodcat.spring_documents.domain.Document;
import su.goodcat.spring_documents.mappers.DocumentMapper;
import su.goodcat.spring_documents.repositories.DocumentRepository;
import su.goodcat.spring_documents.services.interfaces.DocumentService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserFeignClient userFeignClient;

    @Override
    public List<DocumentResponseDTO> getDocumentListByStatus(DocumentRequestDTO documentRequestDTO) {
        List<Status> statusList = documentRequestDTO.status().getStatusList();
        List<Document> documentList = documentRepository.getDocumentsByStatus(statusList);
        return Mappers.getMapper(DocumentMapper.class).fromDocumentToDocumentResponseDTO(documentList);

    }

    @Override
    public ResponseDTOWithDTOList getCounterPartyNotReadDocuments(CounterpartyDocumentRequestDTO counterpartyDocumentRequestDTO, UserDetails userDetails) {
        //меняем местами отправителя и получателя в зависимости от того, входящие или исходящие документы нужны
        long senderId = 0;
        long recipientId = 0;
        if(counterpartyDocumentRequestDTO.getFrontStatus().equals(FrontStatus.INCOMING)){
            senderId = counterpartyDocumentRequestDTO.getCounterpartyID();
            recipientId = Objects.requireNonNull(userFeignClient.searchUserByName(userDetails.getUsername()).getBody()).getId();
        } else if (counterpartyDocumentRequestDTO.getFrontStatus().equals(FrontStatus.OUTCOMING)){
            senderId = Objects.requireNonNull(userFeignClient.searchUserByName(userDetails.getUsername()).getBody()).getId();
            recipientId = counterpartyDocumentRequestDTO.getCounterpartyID();
        }
        List<Document> documentList = documentRepository.getCounterpartyDocument(senderId,recipientId,counterpartyDocumentRequestDTO.getForbiddenStatus());
        return new ResponseDTOWithDTOList(documentList.stream()
                .map(a -> Mappers.getMapper(DocumentMapper.class).fromDocumentToDocumentDTO(a))
                .toList());

    }
}
