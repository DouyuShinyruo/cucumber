package io.github.shinyruo.hellocucumber.agent;

public interface JDBCAgent extends Agent {

    public void connect();

    public void disconnect();

    public void executeQuery(String query);

    public void executeUpdate(String query);
}
