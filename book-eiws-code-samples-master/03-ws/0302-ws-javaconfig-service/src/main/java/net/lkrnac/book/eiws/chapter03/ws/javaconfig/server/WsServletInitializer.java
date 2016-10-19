package net.lkrnac.book.eiws.chapter03.ws.javaconfig.server;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class WsServletInitializer extends
    AbstractAnnotationConfigMessageDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { ServerConfiguration.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/*" };
  }
}
