package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.shinyruo.hellocucumber.stepdefs.common.BaseStepDefinition;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ScenarioSteps extends BaseStepDefinition {

    @Given("an example scenario")
    public void anExampleScenario() {
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
    }

}
