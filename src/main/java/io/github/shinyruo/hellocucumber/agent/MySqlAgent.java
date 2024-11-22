package io.github.shinyruo.hellocucumber.agent;

import java.util.Properties;

public class MySqlAgent extends AbstractAgent implements JDBCAgent {

    public MySqlAgent(String agentName, Properties properties) {
        super(agentName, properties);
    }

    @Override
    public void connect() {
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void executeQuery(String query) {

    }

    @Override
    public void executeUpdate(String query) {

    }
}