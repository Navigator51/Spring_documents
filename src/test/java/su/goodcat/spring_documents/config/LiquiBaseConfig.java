package su.goodcat.spring_documents.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class LiquiBaseConfig {

    @Bean
    public SpringLiquibase configureLiquibase(DataSource dataSource) {

        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDefaultSchema("biplan");
        springLiquibase.setLiquibaseSchema("public");
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:db/changelog.xml");
        springLiquibase.setDropFirst(true);

        return springLiquibase;
    }
}
