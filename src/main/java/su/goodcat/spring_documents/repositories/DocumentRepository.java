package su.goodcat.spring_documents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import su.goodcat.commonlib.domain.Status;
import su.goodcat.spring_documents.domain.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select distinct doc from Document doc left join fetch doc.fileList where doc.status in :statusList")
    List<Document> getDocumentsByStatus(List<Status> statusList);

    @Query("select doc from Document doc left join fetch doc.recipientsId " +
            "where not doc.status = :forbiddenStatus " +
            "and doc.senderId = :senderId and :recipientId in doc.recipientsId ")
    List<Document> getCounterpartyDocument(long senderId, long recipientId, Status forbiddenStatus);
}
