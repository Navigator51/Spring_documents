package su.goodcat.spring_documents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import su.goodcat.spring_documents.domain.Document;
import su.goodcat.spring_documents.domain.Status;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("select distinct doc from Document doc left join fetch doc.fileList where doc.status in :statusList")
    List<Document> getDocumentsByStatus(List<Status> statusList);

}
