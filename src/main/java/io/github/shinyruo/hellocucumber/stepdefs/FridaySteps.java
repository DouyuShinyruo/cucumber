package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.shinyruo.hellocucumber.stepdefs.common.BaseStepDefinition;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FridaySteps extends BaseStepDefinition {

    @Given("today is {today} and stored as var {string}")
    public void todayIsToday(String today, String date) {
        scenarioContext.setContext(date, today);
    }

    @When("read from var {string} and should told me {string}")
    public void iAskWhetherItsFridayYet(String key, String expectedAnswer) {
        final String processedToday = scenarioContext.processString(key);
        final String actualAnswer = isItFriday(processedToday);
        if (actualAnswer.equals(expectedAnswer)) {
            log.info("Today is {}, and it should be {}", processedToday, expectedAnswer);
        } else {
            log.error("Today is {}, but it should be {}", processedToday, expectedAnswer);
        }
    }

    static String isItFriday(String today) {
        if (today.equals("Friday")) {
            return "TGIF";
        }
        return "Nope";
    }
}
