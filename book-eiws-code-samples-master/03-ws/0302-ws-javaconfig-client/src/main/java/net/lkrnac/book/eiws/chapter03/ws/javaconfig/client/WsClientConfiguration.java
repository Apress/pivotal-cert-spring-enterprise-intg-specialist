package net.lkrnac.book.eiws.chapter03.ws.javaconfig.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
@ComponentScan
public class WsClientConfiguration {
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller
        .setContextPath("net.lkrnac.book.eiws.chapter03.ws.javaconfig.model");
    return marshaller;
  }

  @Bean
  public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    webServiceTemplate.setMarshaller(marshaller);
    webServiceTemplate.setUnmarshaller(marshaller);
    webServiceTemplate
        .setDefaultUri("http://localhost:10302/0302-ws-javaconfig-service");
    return webServiceTemplate;
  }
}
