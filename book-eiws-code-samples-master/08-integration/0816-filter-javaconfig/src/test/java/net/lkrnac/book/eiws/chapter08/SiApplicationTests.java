package net.lkrnac.book.eiws.chapter08;

import java.io.IOException;

import net.lkrnac.book.eiws.chapter08.in.SiWrapperServiceVoidAnnotated;
import net.lkrnac.book.eiws.chapter08.out.TestWriteRepository;
import net.lkrnac.book.eiws.chapter08.out.WriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = SiApplication.class)
public class SiApplicationTests extends AbstractTestNGSpringContextTests {
  private static final String MESSAGE_TEXT = "simple message";

  @Autowired
  private WriteRepository writeRepository;

  @Autowired
  private SiWrapperServiceVoidAnnotated wrapperService;

  @Test
  public void testSi() throws InterruptedException, IOException {
    // GIVEN

    // WHEN
    wrapperService.processText(MESSAGE_TEXT);
    wrapperService.processText("corrupted message");
    wrapperService.processText(MESSAGE_TEXT);

    // THEN
    TestWriteRepository testWriteRepository =
        (TestWriteRepository) writeRepository;
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
    Assert.assertEquals(testWriteRepository.getMessage(), MESSAGE_TEXT);
  }
}
