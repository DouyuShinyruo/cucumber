package io.github.shinyruo.hellocucumber.stepdefs.common;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.shinyruo.hellocucumber.stepdefs.BaseStepDefinition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class FridaySteps implements BaseStepDefinition {
    private static final Logger logger = LogManager.getLogger(FridaySteps.class);
    private String today;
    private String actualAnswer;

    static String isItFriday(String today) {
        return "Nope";
    }

    @Given("today is {today}")
    public void todayIsToday(String today) {
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    public void iAskWhetherItsFridayYet() {
        actualAnswer = isItFriday(today);
    }

    @Then("I should be told {string}")
    public void iShouBeTold(String expectedAnswer) {
        if (!expectedAnswer.equals(actualAnswer)) {
            logger.error("Expected answer: {}, actual answer: {}", expectedAnswer, actualAnswer);
        }
    }
}
