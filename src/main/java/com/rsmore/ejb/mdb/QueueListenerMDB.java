package com.rsmore.ejb.mdb;

import java.rmi.activation.UnknownObjectException;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsmore.ejb.entities.Project;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/MyQueue") })
public class QueueListenerMDB implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(QueueListenerMDB.class);
	
	public QueueListenerMDB() {
	}

	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {

				logger.info("Queue: I received a TextMessage at " + new Date());

				TextMessage textMessage = (TextMessage) message;

				System.out.println("Message is " + textMessage.getText());

			} else if (message instanceof ObjectMessage) {
				System.out.println("Queue: I received an ObjectMessage at "
						+ new Date());

				ObjectMessage objectMessage = (ObjectMessage) message;

				if (objectMessage instanceof Project) {

					Project p = (Project) objectMessage;

					System.out.println(p.toString());

				} else {
					throw new UnknownObjectException("Unknown Object "
							+ objectMessage.getClass());
				}

			} else {

			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}