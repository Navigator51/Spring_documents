package su.goodcat.spring_documents.services.interfaces;

import su.goodcat.commonlib.domain.CounterpartyDocumentRequestDTO;
import su.goodcat.commonlib.domain.DocumentRequestDTO;
import su.goodcat.commonlib.domain.DocumentResponseDTO;
import su.goodcat.commonlib.domain.ResponseDTOWithDTOList;

import java.util.List;

public interface DocumentService {

    List<DocumentResponseDTO> getDocumentListByStatus(DocumentRequestDTO documentRequestDTO);

    ResponseDTOWithDTOList getCounterPartyNotReadDocuments(CounterpartyDocumentRequestDTO counterpartyDocumentRequestDTO);
}
