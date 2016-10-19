package net.lkrnac.book.eiws.chapter09.step.tea;

import net.lkrnac.book.eiws.chapter09.step.SimpleExecutablePoint;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoilWaterWithCounter implements Tasklet {
  private SimpleExecutablePoint simpleExecutableStep;

  @Autowired
  public BoilWaterWithCounter(SimpleExecutablePoint simpleExecutableStep) {
    super();
    this.simpleExecutableStep = simpleExecutableStep;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution,
      ChunkContext chunkContext) throws Exception {
    simpleExecutableStep.execute("Boil Water");
    ExecutionContext jobExecutionContext =
        chunkContext.getStepContext().getStepExecution().getJobExecution()
            .getExecutionContext();
    int teaCount = jobExecutionContext.getInt("teaCount", 0);
    teaCount++;
    jobExecutionContext.putInt("teaCount", teaCount);
    return RepeatStatus.FINISHED;
  }
}
