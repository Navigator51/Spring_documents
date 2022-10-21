package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import su.goodcat.spring_documents.controllers.OutFeigh;
import su.goodcat.spring_documents.domain.*;
import su.goodcat.spring_documents.services.interfaces.GetDocumentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDocumentServiceImpl implements GetDocumentService {

    private final OutFeigh outFeigh;


    @Override
    @SuppressWarnings("unchecked")
    public List<Category> getCategoryList() {


        // Делаем запрос во внешний сервис и получаем ответ в виде объекта DictionaryRecordSearch
        DictionaryRecordsSearch drs = Optional.ofNullable(outFeigh.apiV1DictionaryNameSearchPost
                        ("documentTypes",
                                GetDictionaryRequest.builder().includes(List.of("documentTypeGroup", "documentTypeCategory")).build(),
                                1,
                                500)
                .getBody()).orElseThrow();

        // через стрим обрабатываем DictionaryRecordSearch и сетим данные в объекты Type. Получаем список <Type>
        List<Type> typeList = drs.getContent().stream()
                .map(DictionaryRecord::getData)
                .map(a -> (Map<String, Object>) a)
                .map(a -> new Type()
                    .setTypeCode((String) a.get("documentTypeCode"))
                    .setTypeName((String) a.get("documentTypeName"))
                    .setGroupCode((int) getData(a, "documentTypeGroup").get("documentTypeGroupCode"))
                    .setGroupName((String) getData(a, "documentTypeGroup").get("documentTypeGroupName"))
                    .setCategoryCode((int) getData(a, "documentTypeCategory").get("documentTypeCategoryCode"))
                    .setCategoryName((String) getData(a, "documentTypeCategory").get("documentTypeCategoryName")))
                .toList();

        Map<Integer, List<Type>> groupMap = typeList.stream()
                .collect(Collectors.groupingBy(Type::getGroupCode));

        List<Group> groupList = groupMap.values().stream()
                .map(types -> new Group().setGroupeCode(types.get(0).getGroupCode())
                        .setGroupeName(types.get(0).getGroupName())
                        .setCategoryCode(types.get(0).getCategoryCode())
                        .setCategoryName(types.get(0).getCategoryName())
                        .setTypeList(types))
                .toList();

        Map<Integer, List<Group>> categoryMap = groupList.stream()
                .collect(Collectors.groupingBy(Group::getCategoryCode));

        return categoryMap.values().stream()
                .map(groups -> new Category().setGroupList(groups)
                        .setTypeCategoryCode(groups.get(0).getCategoryCode())
                        .setTypeCategoryName(groups.get(0).getCategoryName()))
                .toList();

    }

    // вспомогательный метод для извлечения данных из мапы 2-го уровня вложенности
    @SuppressWarnings("unchecked")
    private Map<String, Object> getData(Map<String, Object> dr, String dictionaryName) {
        Map<String, Object> dataIn = (Map<String, Object>) dr.get(dictionaryName);
        return (Map<String, Object>) dataIn.get("data");
    }


}
