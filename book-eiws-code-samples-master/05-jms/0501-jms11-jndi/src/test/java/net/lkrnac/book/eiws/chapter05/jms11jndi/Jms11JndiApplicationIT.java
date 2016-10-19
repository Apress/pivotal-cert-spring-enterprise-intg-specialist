package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test is relies on separate HornetQ server. During build Maven runs it
 * via hornetq-maven-plugin.
 */
public class Jms11JndiApplicationIT {
  private static final String MESSAGE_TEXT = "dummyMessage";

  @Test(groups = "maventests")
  public void queueTest() throws NamingException, JMSException {
    // GIVEN
    try (JmsConfiguration jmsConfiguration = new JmsConfiguration()) {
      jmsConfiguration.init();
      Connection connection = jmsConfiguration.getConnection();
      Queue queue = jmsConfiguration.getQueue();
      connection.start();

      // WHEN
      SimpleMessageSender messageSender = new SimpleMessageSender();
      messageSender.init(connection, queue);
      messageSender.sendMessage(MESSAGE_TEXT);

      SimpleMessageReader messageReader = new SimpleMessageReader();
      messageReader.init(connection, queue);
      String actualMessage = messageReader.readMessage();

      // THEN
      Assert.assertEquals(MESSAGE_TEXT, actualMessage);
    }
  }
}
