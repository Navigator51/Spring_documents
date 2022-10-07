package su.goodcat.spring_documents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    // включаем транзакции
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}
