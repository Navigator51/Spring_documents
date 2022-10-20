package su.goodcat.spring_documents.services.interfaces;

import su.goodcat.spring_documents.domain.Category;

import java.util.List;

public interface GetDocumentService {

    List<Category> getCategoryList();
}
