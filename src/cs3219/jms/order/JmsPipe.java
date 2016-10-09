package cs3219.jms.order;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Matric 1: A0125372L Name 1: Chiang Jia Feng
 * 
 * Matric 2: A0126400Y Name 2: Quek Yang Sheng
 *
 * This file implements a pipe that transfer messages using JMS.
 */

public class JmsPipe implements IPipe {

    private QueueConnectionFactory qconFactory;
    private QueueConnection qcon;
    private QueueSession qsession;
    private QueueSender qsender;
    private QueueReceiver qreceiver;
    private Queue queue;
    private TextMessage msg;

    public JmsPipe(String jmsFactory, String queueName) throws NamingException, JMSException {
        InitialContext ic = getInitialContext();
        qconFactory = (QueueConnectionFactory) ic.lookup(jmsFactory);
        qcon = qconFactory.createQueueConnection();
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = (Queue) ic.lookup(queueName);
        msg = qsession.createTextMessage();
        qcon.start();
    }

    @Override
    public void write(Order s) {
        try {
            if (qsender == null) {
                qsender = qsession.createSender(queue);
            }
            
            String orderString = s.toString();
            msg.setText(orderString);
            qsender.send(msg);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Order read() {
        if (qreceiver == null) {
            try {
                qreceiver = qsession.createReceiver(queue);
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Order order = null;

        try {
            msg = (TextMessage) qreceiver.receive();
            order = Order.fromString(msg.getText());
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        try {
            if (qsender != null) {
                qsender.close();
            }

            if (qreceiver != null) {
                qreceiver.close();
            }

            qsession.close();
            qcon.close();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static InitialContext getInitialContext() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        return new InitialContext(props);
    }
}
