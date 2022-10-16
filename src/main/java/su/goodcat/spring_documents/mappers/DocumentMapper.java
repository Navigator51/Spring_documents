package su.goodcat.spring_documents.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import su.goodcat.commonlib.domain.DocumentResponseDTO;
import su.goodcat.commonlib.faign.UserFeignClient;
import su.goodcat.spring_documents.config.MapstructConfig;
import su.goodcat.spring_documents.domain.Document;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public abstract class DocumentMapper {

    @Autowired
    private UserFeignClient userFeignClient;

    @Named("getUsernameByDocument")
    public String getUsernameByDocument(Document document) {
        return userFeignClient.searchUserById(document.getSenderId()).getBody().getLogin();
    }

    @Mapping(source = "document", target = "senderUserName", qualifiedByName = "getUsernameByDocument")
    @Mapping(target = "countFiles", expression = "java(document.getFileList().size())")
    abstract DocumentResponseDTO fromDocumentToDocumentResponseDTO(Document document);

    public abstract List<DocumentResponseDTO> fromDocumentToDocumentResponseDTO(List<Document> documentList);
}
