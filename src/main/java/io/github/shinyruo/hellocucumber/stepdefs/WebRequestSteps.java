package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.shinyruo.hellocucumber.agent.WebRequestAgent;
import io.github.shinyruo.hellocucumber.stepdefs.common.BaseStepDefinition;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class WebRequestSteps extends BaseStepDefinition {
    @Given("{word} sets the request header")
    public void setRequestHeader(String agentName, Map<String, String> headers) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        headers.forEach(webRequestAgent::setHeader);
    }

    @Given("{word} sets the request body")
    public void setRequestBody(String agentName, String body) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        final String processedBody = scenarioContext.processString(body);
        webRequestAgent.setBody(processedBody);
    }

    @Then("{word} send a {httpMethod} request to {string}")
    public void sendRequestTo(String agentName, Method httpMethod, String url) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        final String processedUrl = scenarioContext.processString(url);
        webRequestAgent.buildGivenBasicRequest(processedUrl);
        webRequestAgent.sendRequest(httpMethod);
    }

    @Then("{word} should receive a response with status code {int}")
    public void verifyResponseStatusCode(String agentName, int statusCode) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("{word} receive a response with body {string}")
    public void verifyResponseBody(String agentName, String expectedBody) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        final String processedExpectedBody = scenarioContext.processString(expectedBody);
        assertEquals(processedExpectedBody, response.getBody().asString());
    }

    @Then("{word} receive a response with body contains {int}")
    public void verifyResponseBodyContains(String agentName, int expectedStatusCode) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("{word} receive a response with body contains {string}")
    public void verifyResponseBodyContains(String agentName, String expectedBody) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) agentManager.getOrCreateAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        final String processedExpectedBody = scenarioContext.processString(expectedBody);
        assertTrue(response.getBody().asString().contains(processedExpectedBody));
    }
}
