package io.github.shinyruo.hellocucumber.fw;


import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.shinyruo.hellocucumber.agent.AgentManager;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CucumberHooks {
    private final ScenarioContext scenarioContext;
    private final AgentManager agentManager;

    @Inject
    public CucumberHooks(ScenarioContext scenarioContext, AgentManager agentManager) {
        this.scenarioContext = scenarioContext;
        this.agentManager = agentManager;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("=======Starting scenario: {} =======", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        scenarioContext.cleanup();
        agentManager.cleanup();
        log.info("=======Ending scenario: {} =======", scenario.getName());
    }

}
