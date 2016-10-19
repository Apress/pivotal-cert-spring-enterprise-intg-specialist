package net.lkrnac.book.eiws.chapter03.ws.interceptor.client;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.lkrnac.book.eiws.ProcessExecutor;
import net.lkrnac.book.eiws.FunctionRetryHandler;
import net.lkrnac.book.eiws.chapter03.ws.interceptor.model.UserDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { WsInterceptorClientConfiguration.class })
@DirtiesContext
public class WsInterceptorE2ETest extends AbstractTestNGSpringContextTests {
  private static final int RETRY_TIMEOUT = 20000;

  @Autowired
  private WebServiceClient wsClient;

  @Test(groups = "maventests")
  public void testGetUserDetails() throws IOException, InterruptedException {
    // GIVEN
    Process process =
        new ProcessExecutor().execute("0305-ws-interceptor-service.jar");
    try {

      FunctionRetryHandler<String, UserDetailsResponse> retryHandler =
          new FunctionRetryHandler<>();

      // WHEN
      UserDetailsResponse userDetails =
          retryHandler.retry(wsClient::getUserDetails, "lubos.krnac@gmail.com",
              RETRY_TIMEOUT);

      // THEN
      assertEquals(userDetails.getFirstName(), "Lubos");
      assertEquals(userDetails.getLastName(), "Krnac");
    } finally {
      process.destroyForcibly();
      process.waitFor();
    }
  }
}
