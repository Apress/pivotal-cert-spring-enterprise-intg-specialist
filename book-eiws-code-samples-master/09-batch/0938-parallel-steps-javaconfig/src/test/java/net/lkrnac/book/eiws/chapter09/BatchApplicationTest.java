package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ActiveProfiles("integration-test")
@SpringApplicationConfiguration(classes = BatchApplication.class)
public class BatchApplicationTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private SimpleExecutablePoint executableStep;

  @Test(timeOut = 5000)
  public void testBatch() {
    // GIVEN - Spring configuration

    // WHEN - Spring Batch job is started automatically

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    String concurrentRecord1 = testExecutableStep.getMessage();
    String concurrentRecord2 = testExecutableStep.getMessage();
    if ("Add Tea".equals(concurrentRecord1)) {
      Assert.assertEquals(concurrentRecord2, "Add one spoon of sugar");
    } else {
      Assert.assertEquals(concurrentRecord2, "Add Tea");
    }
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
  }
}
