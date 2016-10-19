package net.lkrnac.book.eiws.chapter09;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.lkrnac.book.eiws.chapter09.step.tea.AddTea;
import net.lkrnac.book.eiws.chapter09.step.tea.AddWaterWithCounter;
import net.lkrnac.book.eiws.chapter09.step.tea.BoilWaterWithCounter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  @Bean
  public Step boilWaterStep(StepBuilderFactory stepFactory,
      BoilWaterWithCounter boilWater) {
    return stepFactory.get("boilWaterStep").tasklet(boilWater)
        .allowStartIfComplete(true).build();
  }

  @Bean
  public Step addTeaStep(StepBuilderFactory stepFactory, AddTea addTea) {
    return stepFactory.get("addTeaStep").tasklet(addTea)
        .allowStartIfComplete(true).build();
  }

  @Bean
  public Step addWaterStep(StepBuilderFactory stepFactory, AddWaterWithCounter addWater) {
    return stepFactory.get("addWaterStep").tasklet(addWater)
        .allowStartIfComplete(true).build();
  }

  @Bean
  public Job prepareTeaJob(JobBuilderFactory jobBuilderFactory,
      @Qualifier("boilWaterStep") Step boilWaterStep,
      @Qualifier("addTeaStep") Step addTeaStep,
      @Qualifier("addWaterStep") Step addWaterStep) {
    return jobBuilderFactory.get("prepareTeaJob")
        .start(boilWaterStep)
        .next(addTeaStep)
        .next(addWaterStep)
        .build();
  }
}
