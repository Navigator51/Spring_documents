package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.goodcat.spring_documents.controllers.OutFeigh;
import su.goodcat.spring_documents.domain.*;
import su.goodcat.spring_documents.services.interfaces.GetDocumentService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDocumentServiceImpl implements GetDocumentService {

    private final OutFeigh outFeigh;


    @Override
    public List<Category> getCategoryList() {
        Type type = new Type();
        List<Type> typeList = null;

        // Делаем запрос ыо внешний сервис и получаем ответ в виде обекта DRS
        DictionaryRecordsSearch drs = outFeigh.apiV1DictionaryNameSearchPost("documentTypes",
                GetDictionaryRequest.builder().includes(List.of("documentTypeGroup", "documentTypeCategory")).build(),
                1,
                500).getBody();

        // через стрим обрабатываем DRS и сетим данные в объекты Type. Получаем список <Type>
        drs.getContent()
                .stream()
                .map(DictionaryRecord::getData)
                .map(a -> (LinkedHashMap<String, Object>) a)
                .peek(a -> {type.setTypeCode((String) a.get("documentTypeCode"));
                             type.setTypeName((String) a.get("documentTypeName"));
                            type.setGroupCode((int) getGroupData(a).get("documentTypeGroupCode"));
                            type.setGroupName((String) getGroupData(a).get("documentTypeGroupName"));
                            type.setCategoryCode((int) getCategoryData(a).get("documentTypeCategoryCode"));
                            type.setCategoryName((String) getCategoryData(a).get("documentTypeCategoryName"));
                            typeList.add(type);})
                .close();
        return null;
    }

    private HashMap<String, Object> getGroupData(LinkedHashMap<String, Object> dr) {
        HashMap<String, Object> dataIn = (HashMap<String, Object>) dr.get("documentTypeGroup");
        return (HashMap<String, Object>) dataIn.get("data");
    }

    private HashMap<String, Object> getCategoryData(LinkedHashMap<String, Object> dr) {
        HashMap<String, Object> dataIn = (HashMap<String, Object>) dr.get("documentTypeCategory");
        return (HashMap<String, Object>) dataIn.get("data");
    }
}
