package net.lkrnac.book.eiws.chapter09;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Configuration
@ComponentScan
public class BatchApplication {
  public static void main(String[] args) throws Exception {
    GenericApplicationContext context =
        new AnnotationConfigApplicationContext(BatchApplication.class);

    JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
    Job job = (Job) context.getBean("prepareTeaJob");
    JobExecution execution = jobLauncher.run(job, new JobParameters());
    log.info("Exit Status: {}", execution.getStatus());

    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean(JdbcTemplate.class);
    long stepExecutionCount =
        jdbcTemplate.queryForObject("select count(*) from BATCH_STEP_EXECUTION",
            Long.class);
    log.info("Number of steps executed: {}", stepExecutionCount);
    context.close();
  }
}
