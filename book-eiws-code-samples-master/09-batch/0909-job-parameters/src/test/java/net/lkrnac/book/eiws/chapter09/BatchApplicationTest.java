package net.lkrnac.book.eiws.chapter09;

import java.util.HashMap;
import java.util.Map;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;
import net.lkrnac.book.eiws.chapter09.step.TestExecutablePoint;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
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

  @Autowired
  private Job job;

  @Autowired
  private JobLauncher jobLauncher;

  @Test(timeOut = 5000)
  public void testBatch() throws Exception {
    // GIVEN - Spring configuration
    Map<String, JobParameter> jobParametersMap = new HashMap<>();
    jobParametersMap.put("sugarAmount", new JobParameter("no sugar"));
    JobParameters jobParameters = new JobParameters(jobParametersMap);

    // WHEN
    JobExecution execution = jobLauncher.run(job, jobParameters);

    // THEN
    TestExecutablePoint testExecutableStep =
        (TestExecutablePoint) executableStep;
    Assert.assertEquals(testExecutableStep.getMessage(), "Boil Water");
    Assert.assertEquals(testExecutableStep.getMessage(),
        "Add Tea with no sugar");
    Assert.assertEquals(testExecutableStep.getMessage(), "Add Water");
    Assert.assertEquals(execution.getStatus(), BatchStatus.COMPLETED);
  }
}
