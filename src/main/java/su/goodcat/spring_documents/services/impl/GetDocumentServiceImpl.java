package su.goodcat.spring_documents.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import su.goodcat.spring_documents.feign.OutFeigh;
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
                        (GetDictionaryRequest.builder().includes(List.of("documentTypeGroup", "documentTypeCategory")).build(),
                                "documentTypes",
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

        // делаем группировку в мапу, где ключём является пара значений  код "группы-код категории"
        // для того, чтобы в будущем издежать неодносначности при фогмировании категории.
        Map<Pair<Integer, Integer>, List<Type>> groupMap = typeList.stream()
                .collect(Collectors.groupingBy(type -> new ImmutablePair<>(type.getGroupCode(), type.getCategoryCode())));

        // Сетим группы и собираем их в список. Перед сетом списка типов в нём делаем сортировку по имени.
        List<Group> groupList = groupMap.entrySet().stream()
                .map(entry -> new Group().setGroupeCode(entry.getKey().getLeft())
                        .setGroupeName(entry.getValue().get(0).getGroupName())
                        .setCategoryCode(entry.getKey().getRight())
                        .setCategoryName(entry.getValue().get(0).getCategoryName())
                        .setTypeList(entry.getValue()
                                .stream().sorted(Comparator.comparing(Type::getTypeName))
                                .toList()))
                .toList();

        // Группируем группы в мапу, где ключ это код категория
        Map<Integer, List<Group>> categoryMap = groupList.stream()
                .collect(Collectors.groupingBy(Group::getCategoryCode));

        // Сетим категории. При этом сортируем списки групп по имени
        // Собираем и возвращаем список категорий. Перед этим сортируем список по имени категорий.
        return categoryMap.values().stream()
                .map(groups -> new Category()
                        .setTypeCategoryCode(groups.get(0).getCategoryCode())
                        .setTypeCategoryName(groups.get(0).getCategoryName())
                        .setGroupList(groups.stream()
                                .sorted(Comparator.comparing(Group::getGroupeName))
                                .toList()))
                .sorted(Comparator.comparing(Category::getTypeCategoryName))
                .toList();

    }

    // вспомогательный метод для извлечения данных из мапы 2-го уровня вложенности
    @SuppressWarnings("unchecked")
    private Map<String, Object> getData(Map<String, Object> dr, String dictionaryName) {
        Map<String, Object> dataIn = (Map<String, Object>) dr.get(dictionaryName);
        return (Map<String, Object>) dataIn.get("data");
    }


}
