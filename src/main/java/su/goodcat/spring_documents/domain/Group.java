package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class Group {

    private final int groupeCode;
    private final String groupeName;
    private final List<Type> typeList;
    @JsonIgnore
    private final int categoryCode;
}
