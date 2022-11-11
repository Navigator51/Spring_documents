package su.goodcat.spring_documents.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import su.goodcat.commonlib.domain.CounterpartyDocumentRequestDTO;
import su.goodcat.commonlib.domain.DocumentRequestDTO;
import su.goodcat.commonlib.domain.DocumentResponseDTO;
import su.goodcat.commonlib.domain.ResponseDTOWithDTOList;
import su.goodcat.spring_documents.domain.Category;
import su.goodcat.spring_documents.services.interfaces.DocumentService;
import su.goodcat.spring_documents.services.interfaces.GetDocumentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "document-controller",
        description = "Контроллер для работы с документами"
)
public class DocumentController {

    private final DocumentService documentService;
    private final GetDocumentService getDocumentService;

    @PostMapping(path = "/api/v1/document/status")
    public ResponseEntity<List<DocumentResponseDTO>> getDocumentByStatus(@RequestBody DocumentRequestDTO documentRequestDTO) {
        return ResponseEntity.ok(documentService.getDocumentListByStatus(documentRequestDTO));
    }

    @GetMapping(path = "/api/v1/document/category")
    public ResponseEntity<List<Category>> getCategoryList() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping(path = "/api/v1/document/counterparty/read")
    public ResponseEntity<ResponseDTOWithDTOList> getDocumentsByCounterparty(@RequestBody CounterpartyDocumentRequestDTO counterpartyDocumentRequestDTO) {
        return ResponseEntity.ok(documentService.getCounterPartyNotReadDocuments(counterpartyDocumentRequestDTO));
    }

}
