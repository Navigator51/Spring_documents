package su.goodcat.spring_documents.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import su.goodcat.spring_documents.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig implements WebMvcConfigurer {

    private final UserDetailsServiceImpl userDetailsService;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        return httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll() // доступность всем
                .antMatchers("/").authenticated()
                .anyRequest().permitAll()
                .and().build();
    }

    @Autowired
    public void setUserDetailsService(AuthenticationManagerBuilder autMB) throws Exception {
        autMB.userDetailsService(userDetailsService);
    }
}
