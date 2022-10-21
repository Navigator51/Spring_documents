package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class Group {

    private  int groupeCode;
    private  String groupeName;
    private  List<Type> typeList;
    @JsonIgnore
    private  int categoryCode;
    @JsonIgnore
    private String categoryName;
}
