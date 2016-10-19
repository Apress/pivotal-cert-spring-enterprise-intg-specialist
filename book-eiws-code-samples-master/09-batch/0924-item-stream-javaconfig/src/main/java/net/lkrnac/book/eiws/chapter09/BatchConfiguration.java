package net.lkrnac.book.eiws.chapter09;

import net.lkrnac.book.eiws.chapter09.process.SimpleRecordProcessor;
import net.lkrnac.book.eiws.chapter09.read.SimpleRecordReader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Bean
  public Step simpleRecordsStep(StepBuilderFactory stepBuilderFactory,
      SimpleRecordReader simpleRecordReader,
      SimpleRecordProcessor simpleRecordProcessor,
      StreamRecordWriter StreamRecordWriter,
      RecordsReaderItemStream recordsReaderItemStream) {
    return stepBuilderFactory.get("simpleRecordsStep")
        .<String, String> chunk(4)
        .reader(simpleRecordReader)
        .processor(simpleRecordProcessor)
        .writer(StreamRecordWriter)
        .stream(recordsReaderItemStream)
        .build();
  }

  @Bean
  public Job simpleRecordsJob(JobBuilderFactory jobBuilderFactory,
      Step simpleRecordsStep) {
    return jobBuilderFactory.get("simpleRecordsJob")
        .start(simpleRecordsStep)
        .build();
  }
}
