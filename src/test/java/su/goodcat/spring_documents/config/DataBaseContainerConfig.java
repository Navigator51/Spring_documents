package su.goodcat.spring_documents.config;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@Import(LiquiBaseConfig.class)
public class DataBaseContainerConfig {

    private final static String postgresImageName = "postgres:12";
    private final static String dataBaseName = "postgres";

    protected static final PostgreSQLContainer<?> postgreSQL = findConteinerPort();


    @BeforeAll
    public static void inicializeContainer() {
        postgreSQL.start();
    }

    private static PostgreSQLContainer<?> findConteinerPort() {
        PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>(postgresImageName)
                .withDatabaseName(dataBaseName)
                .withUsername("NIIChaVo")
                .withPassword("123456")
                .withReuse(true);
        postgreSQL.setPortBindings(List.of("5432:5433"));
        return postgreSQL;
    }
}
