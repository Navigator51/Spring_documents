package su.goodcat.spring_documents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Type {

    private  String typeCode;
    private  String typeName;
    @JsonIgnore
    private  int groupCode;
    @JsonIgnore
    private String groupName;
    @JsonIgnore
    private  int categoryCode;
    @JsonIgnore
    private String categoryName;
}
