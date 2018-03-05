package fwd.farmer.dispatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fwd.common.main.PotatoDelivery;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class MqDispatch implements FarmerDispatch {

    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    private Logger logger;

    public MqDispatch() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://fwd_activemq_1:61616");

        logger = LoggerFactory.getLogger(FarmerDispatch.class);

        try {
            connection = connectionFactory.createConnection();

            connection.setClientID("fwd_farmer_1");

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue queue = session.createQueue("farmer_factory_dispatch");

            messageProducer = session.createProducer(queue);
        }
        catch (JMSException e) {
            logger.error("Could not init ActiveMQ topic: ", e.getMessage());
        }
    }

    @Override
    public void dispatch(PotatoDelivery delivery) throws DispatchFailedException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            TextMessage message = session.createTextMessage(mapper.writeValueAsString(delivery));

            messageProducer.send(message);
        } catch (JsonProcessingException e) {
            throw new DispatchFailedException();
        } catch (JMSException e) {
            throw new DispatchFailedException();
        }
    }
}
