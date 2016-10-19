package net.lkrnac.book.eiws.chapter03.ws.javaconfig.config;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter03.ws.javaconfig.client.WebServiceClient;
import net.lkrnac.book.eiws.chapter03.ws.javaconfig.client.WsClientConfiguration;
import net.lkrnac.book.eiws.chapter03.ws.javaconfig.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(WsClientConfiguration.class)
@EnableAutoConfiguration
public class WsJavaConfigClientApplication {
  @Autowired
  private WebServiceClient wsClient;

  public static void main(String[] args) {
    SpringApplication.run(WsJavaConfigClientApplication.class, args);
  }

  @PostConstruct
  public void test() {
    UserDetailsResponse userDetails =
        wsClient.getUserDetails("lubos.krnac@gmail.com");
    log.info("User Details: " + userDetails.getFirstName() + " "
        + userDetails.getLastName());
  }
}
