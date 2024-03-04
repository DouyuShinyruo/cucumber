package io.github.shinyruo.hellocucumber.fw;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;
import io.github.shinyruo.hellocucumber.agent.AgentManager;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PropertiesHandler.class).asEagerSingleton();
        bind(AgentManager.class).asEagerSingleton();
        bind(ScenarioContext.class).asEagerSingleton();
    }
}
