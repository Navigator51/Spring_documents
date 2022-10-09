package su.goodcat.spring_documents.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Role implements GrantedAuthority {

    public Long id;

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

}

