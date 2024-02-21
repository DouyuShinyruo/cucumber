package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.ParameterType;
import io.github.shinyruo.hellocucumber.stepdefs.enums.BrowserEnum;
import io.restassured.http.Method;
import org.openqa.selenium.WebDriver;

public class RegisteredParameterType {
    @ParameterType("Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday")
    public String today(String today) {
        return today;
    }

    @ParameterType("chrome|edge|firefox|ie|safari")
    public WebDriver browser(String browser) {
        return BrowserEnum.valueOf(browser.toUpperCase()).createDriver();
    }

    @ParameterType("GET|POST|PUT|DELETE|PATCH|OPTIONS|HEAD|TRACE")
    public Method httpMethod(String method) {
        return Method.valueOf(method);
    }
}