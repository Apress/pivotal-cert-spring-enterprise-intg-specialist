package net.lkrnac.book.eiws.chapter08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.core.MessagingTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:si-config.xml")
public class SiApplication {
  public static void main(String[] args) throws InterruptedException {
    ConfigurableApplicationContext context =
        SpringApplication.run(SiApplication.class, args);

    MessagingTemplate messagingTemplate = context.getBean(MessagingTemplate.class);
    boolean result = messagingTemplate.convertSendAndReceive(
        "inChannel", "simple message", Boolean.class);
    log.info("Result: " + result);
    context.close();
  }
}
