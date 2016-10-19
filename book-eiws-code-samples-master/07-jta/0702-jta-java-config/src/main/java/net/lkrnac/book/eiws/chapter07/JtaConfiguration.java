package net.lkrnac.book.eiws.chapter07;

import javax.transaction.SystemException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
public class JtaConfiguration {
  @Bean(initMethod = "init", destroyMethod = "close")
  public UserTransactionManager atomikosTransactionManager()
      throws SystemException {
    UserTransactionManager userTransactionManager =
        new UserTransactionManager();
    userTransactionManager.setTransactionTimeout(600000);
    return userTransactionManager;
  }

  @Bean
  public JtaTransactionManager transactionManager(
      UserTransactionManager atomikosTransactionManager) {
    JtaTransactionManager transactionManager = new JtaTransactionManager();
    transactionManager.setTransactionManager(atomikosTransactionManager);
    transactionManager.setUserTransaction(atomikosTransactionManager);
    return transactionManager;
  }
}
