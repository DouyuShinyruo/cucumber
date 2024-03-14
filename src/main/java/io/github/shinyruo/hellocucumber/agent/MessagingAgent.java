package io.github.shinyruo.hellocucumber.agent;

public interface MessagingAgent extends Agent {

    public void sendMessage(String message);

    public String receiveMessage();
}
