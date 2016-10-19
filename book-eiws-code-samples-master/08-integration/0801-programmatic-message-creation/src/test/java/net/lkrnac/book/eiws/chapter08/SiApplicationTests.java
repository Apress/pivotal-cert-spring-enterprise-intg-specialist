package net.lkrnac.book.eiws.chapter08;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceMessage;
import net.lkrnac.book.eiws.chapter08.out.WriteRepositoryWithHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  @Autowired
  private WriteRepositoryWithHeaders writeRepository;

  @Autowired
  private SiWrapperServiceMessage wrapperService;

  @Test
  public void testSi() {
    // GIVEN
    Message<String> message =
        MessageBuilder.withPayload("simple message")
            .setHeader("simpleHeader", "simple header").build();

    // WHEN
    wrapperService.processMessage(message);

    // THEN
    TestWriteRepositoryWithHeaders testWriteRepository =
        (TestWriteRepositoryWithHeaders) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), new String[] {
        "simple message", "simple header" });
  }
}
