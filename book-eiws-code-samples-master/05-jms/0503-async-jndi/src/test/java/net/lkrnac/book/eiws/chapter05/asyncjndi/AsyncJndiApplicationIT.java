package net.lkrnac.book.eiws.chapter05.asyncjndi;

import javax.jms.JMSContext;
import javax.jms.Queue;

import net.lkrnac.book.eiws.chapter05.text.test.TestingSimpleService;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test is relies on separate HornetQ server. During build Maven runs it
 * via hornetq-maven-plugin.
 */
public class AsyncJndiApplicationIT {
  private static final String MESSAGE_TEXT = "dummyMessage";

  @Test(groups = "maventests")
  public void queueTest() throws Exception {
    // GIVEN
    TestingSimpleService simpleService =
        new TestingSimpleService();
    try (JmsConfiguration jmsConfiguration =
        new JmsConfiguration(simpleService)) {
      jmsConfiguration.init();
      JMSContext jmsContext = jmsConfiguration.getJmsContext();
      Queue queue = jmsConfiguration.getQueue();

      // WHEN
      SimpleMessageSender messageSender =
          new SimpleMessageSender(jmsContext, queue);
      messageSender.sendMessage(MESSAGE_TEXT);

      // THEN
      Assert.assertEquals(simpleService.getMessage(), MESSAGE_TEXT);
    }
  }
}
