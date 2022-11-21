package su.goodcat.spring_documents.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import su.goodcat.commonlib.domain.*;
import su.goodcat.spring_documents.domain.Document;
import su.goodcat.spring_documents.repositories.DocumentRepository;

import java.time.LocalDateTime;
import java.util.List;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Test
    @DisplayName("Тестирование метода getCounterPartyNotReadDocuments")
    void getCounterPartyNotReadDocuments() {
        //given
        CounterpartyDocumentRequestDTO counterpartyDocumentRequestDTO =
                new CounterpartyDocumentRequestDTO(FrontStatus.INCOMING, Status.DRAFT, 4L, 1L);
        LocalDateTime a = LocalDateTime.of(2021, 12, 11, 7, 16);

        when(documentRepository.getCounterpartyDocument(4L, 1L, Status.DRAFT))
                .thenReturn(List.of(128L, 46L));

        when(documentRepository.findAllById(List.of(128L, 46L)))
                .thenReturn(List.of(new Document().setId(128L).setModifyDate(LocalDateTime.of(2022, 3,16,12,15)),
                        new Document().setId(46L).setModifyDate(a)));

        //when
        ResponseDTOWithDTOList result = documentService.getCounterPartyNotReadDocuments(counterpartyDocumentRequestDTO);

        //then
        assertThat(result.documentDTOList()).hasSize(2);
        assertThat(result.documentDTOList().get(0).documentId()).isEqualTo(128L);
        assertThat(result.documentDTOList().get(1).modifyDate()).isEqualTo(a);
    }
}