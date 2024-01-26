package io.github.shinyruo.hellocucumber.stepdefs.common;

import io.cucumber.java.en.*;

import io.github.shinyruo.hellocucumber.stepdefs.BaseStepDefinition;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridaySteps extends BaseStepDefinition {
    private static final Logger LOGGER = LogManager.getLogger(FridaySteps.class);
    private String today;
    private String actualAnswer;

    static String isItFriday(String today) {
        return "Nope";
    }

    @Given("today is {today}")
    public void today_is_Sunday(String today) {
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
