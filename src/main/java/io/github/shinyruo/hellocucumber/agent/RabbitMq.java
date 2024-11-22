package io.github.shinyruo.hellocucumber.agent;

import java.util.Properties;

public class RabbitMq extends AbstractAgent implements MessagingAgent {

    public RabbitMq(String agentName, Properties properties) {
        super(agentName, properties);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
    }

    @Override
    public String receiveMessage() {
        return "Received message";
    }
}
