package com.shinyruo.hellocucumber.stepdefs.common;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Friday {
    private String today;
    private String actualAnswer;

    static String isItFriday(String today) {
        return "Nope";
    }

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
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
