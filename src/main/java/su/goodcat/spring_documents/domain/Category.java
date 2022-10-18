package su.goodcat.spring_documents.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Category {

    private final int typeCategoryCode;
    private final String typeCategoryName;
    private final List<Group> groupList;

}
