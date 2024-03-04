package io.github.shinyruo.hellocucumber.stepdefs.common;

import com.google.inject.Inject;
import io.github.shinyruo.hellocucumber.agent.AgentManager;
import io.github.shinyruo.hellocucumber.fw.ScenarioContext;

public class BaseStepDefinition {
    @Inject
    protected AgentManager agentManager;
    @Inject
    protected ScenarioContext scenarioContext;

}
