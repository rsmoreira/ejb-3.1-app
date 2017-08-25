package com.rsmore.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsmore.ejb.beans.stateless.CalculatorBean;

@MessageDriven(

		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ChatQueue") 
		}
)
public class ChatBean implements MessageListener {

	private static final Logger logger = LoggerFactory
			.getLogger(CalculatorBean.class);

	@Resource
	private ConnectionFactory connectionFactory;

	@Resource(name = "AnswerQueue")
	private Queue answerQueue;

	public ChatBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {

		try {

			logger.info(this.getClass().getCanonicalName()
					+ " - onMessage(Message) method called.");

			if (message instanceof TextMessage) {
				final TextMessage textMessage = (TextMessage) message;
				final String question = textMessage.getText();

				if ("Hello World!".equalsIgnoreCase(question)) {
					respond("Hello, test case");

				} else if ("How are you?".equalsIgnoreCase(question)) {
					respond("I'm fine!");
				} else if ("Still spinning?".equalsIgnoreCase(question)) {
					respond("Yah dude.");
				} else {
					respond("Bahh");
				}

			}

		} catch (JMSException e) {
			throw new IllegalStateException();
		}
	}

	/**
	 * 
	 * Method create to respond to JMS Caller.
	 * 
	 * Will it work sending messages back to Spring?
	 * 
	 * Spring doesn't has an EJB structure, could I create an JMS there to get
	 * these responses sent here? Need I to do this?
	 * 
	 * My Spring app is running into Tomcat.
	 * 
	 * 
	 * @param text
	 * @throws JMSException
	 */
	private void respond(String text) throws JMSException {

		Connection connection = null;
		Session session = null;

		logger.info(this.getClass().getCanonicalName()
				+ " - responde(String) method called.");

		try {

			// create and start the connection
			connection = connectionFactory.createConnection();
			connection.start();

			// create the session
			session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);

			// create a message producer from session to the topic or queue
			MessageProducer producer = session.createProducer(answerQueue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// create a message
			TextMessage message = session.createTextMessage(text);

			// tell the producer to send the message
			producer.send(message);

		} finally {
			// Clean up!
			if (session != null)
				session.close();
			if (connection != null)
				connection.close();
		}
	}
}