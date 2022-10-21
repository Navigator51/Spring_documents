package su.goodcat.spring_documents.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Category {

    private  int typeCategoryCode;
    private  String typeCategoryName;
    private  List<Group> groupList;

}
