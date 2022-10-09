package su.goodcat.spring_documents.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import su.goodcat.spring_documents.config.MapstructConfig;
import su.goodcat.spring_documents.domain.Document;
import su.goodcat.spring_documents.domain.dto.DocumentResponseDTO;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface DocumentMapper {
// TODO создать запрос, возвращающий инфу о юзере
    // @Mapping(source = "sender.name", target = "senderUserName")
    @Mapping(target = "countFiles", expression = "java(document.getFileList().size())")
    DocumentResponseDTO fromDocumentToDocumentResponseDTO(Document document);

    List<DocumentResponseDTO> fromDocumentToDocumentResponseDTO(List<Document> documentList);
}
