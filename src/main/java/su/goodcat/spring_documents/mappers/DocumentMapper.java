package su.goodcat.spring_documents.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import su.goodcat.commonlib.domain.DocumentDTO;
import su.goodcat.commonlib.domain.DocumentResponseDTO;
import su.goodcat.commonlib.feign.UserFeignClient;
import su.goodcat.spring_documents.config.MapstructConfig;
import su.goodcat.spring_documents.domain.Document;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Mapper(config = MapstructConfig.class)
public abstract class DocumentMapper {

    @Autowired
    private UserFeignClient userFeignClient;

    @Named("getUsernameByDocument")
    public String getUsernameByDocument(Document document) {
        return Objects.requireNonNull(userFeignClient.searchUserById(document.getSenderId()).getBody(),
                "Не найден пользователь с запрошенным именем").getLogin();
    }

    @Mapping(source = "document", target = "senderUserName", qualifiedByName = "getUsernameByDocument")
    @Mapping(target = "countFiles", expression = "java(document.getFileList().size())")
    abstract DocumentResponseDTO fromDocumentToDocumentResponseDTO(Document document);

    public abstract List<DocumentResponseDTO> fromDocumentToDocumentResponseDTO(List<Document> documentList);

    @Mapping(source = "id", target = "documentId")
    public abstract DocumentDTO fromDocumentToDocumentDTO(Document document);
    public abstract List<DocumentDTO> fromDocumentToDocumentDTO(Collection<Document> documentCollection);
}
