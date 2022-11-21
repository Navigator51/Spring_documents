package su.goodcat.spring_documents.repositories;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import su.goodcat.commonlib.domain.Status;
import su.goodcat.spring_documents.config.DataBaseContainerConfig;
import su.goodcat.spring_documents.domain.Document;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentRepositoryTest extends DataBaseContainerConfig {

    @Autowired
    private DocumentRepository documentRepository;

    @AfterEach
    void cleanDB() {
        documentRepository.deleteAll();
    }

    @Test
    void getCounterpartyDocument() {
        Document document1 = new Document();
        document1.setSenderId(1L).setCreatorId(1L).setRecipientsId(List.of(2L, 4L, 5L)).setStatus(Status.AGREED);
        Document document2 = new Document();
        document2.setSenderId(4L).setCreatorId(4L).setRecipientsId(List.of(1L, 2L, 3L)).setStatus(Status.PREPARED);
        Document document3 = new Document();
        document3.setSenderId(3L).setCreatorId(3L).setRecipientsId(List.of(1L, 2L, 4L)).setStatus(Status.DRAFT);
        documentRepository.saveAll(List.of(document1, document2, document3));

        List<Long> idList = documentRepository.getCounterpartyDocument(1L, 2L, Status.DRAFT);
        List<Document>documentList = documentRepository.findByAllDependensies(idList);

        assertThat(documentList.get(0)).usingRecursiveComparison()
                .ignoringFields("creationDate", "modifyDate", "fileList")
                .isEqualTo(document1);
    }
}