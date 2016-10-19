package net.lkrnac.book.eiws.chapter05.jms11jndi;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SimpleMessageReader {
  private MessageConsumer messageConsumer;
  private Session session;

  public void init(Connection connection, Queue queue) throws JMSException {
    session = connection.createSession(true, Session.SESSION_TRANSACTED);
    messageConsumer = session.createConsumer(queue);
  }

  public String readMessage() throws JMSException {
    TextMessage messageReceived = (TextMessage) messageConsumer.receive(5000);
    session.commit();
    return messageReceived.getText();
  }
}
