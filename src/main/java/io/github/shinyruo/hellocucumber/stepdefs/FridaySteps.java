package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FridaySteps {
    private static final Logger logger = LogManager.getLogger(FridaySteps.class);
    private String today;
    private String actualAnswer;


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
        logger.info("Today is {}, expected answer: {}, actual answer: {}", today, expectedAnswer, actualAnswer);
        if (!expectedAnswer.equals(actualAnswer)) {
            logger.error("Expected answer: {}, actual answer: {}", expectedAnswer, actualAnswer);
        }
    }

    static String isItFriday(String today) {
        if (today.equals("Friday")) {
            return "TGIF";
        }
        return "Nope";
    }
}
