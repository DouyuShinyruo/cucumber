package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.ParameterType;

public class RegisteredParameterType extends BaseStepDefinition {
    @ParameterType("Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday")
    public String today(String today) {
        return today;
    }
}
