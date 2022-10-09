package su.goodcat.spring_documents.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



    public class User implements UserDetails {


        private long id;


        private String name;


        private String middleName;

        private String surname;

        private LocalDate bornDate;

        private String about;


        private LocalDateTime creationDateTime;


        private LocalDateTime modifyDateTime;

        private Role role = Role.builder()
                .id(1L)
                .build();


        private String login;


        private String password;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(role);
        }

        @Override
        public String getUsername() {
            return login;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }


