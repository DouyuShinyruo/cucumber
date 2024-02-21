package io.github.shinyruo.hellocucumber.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.shinyruo.hellocucumber.agent.AgentManager;
import io.github.shinyruo.hellocucumber.agent.WebRequestAgent;
import io.github.shinyruo.hellocucumber.common.ScenarioContext;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebRequestSteps {
    @Given("{word} sets the request header")
    public void setRequestHeader(String agentName, Map<String, String> headers) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) AgentManager.getInstance().getOrCreatAgent(agentName);
        headers.forEach(webRequestAgent::setHeader);
    }

    @Given("{word} sets the request body")
    public void setRequestBody(String agentName, String body) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) AgentManager.getInstance().getOrCreatAgent(agentName);
        final String processedBody = ScenarioContext.getInstance().processString(body);
        webRequestAgent.setBody(processedBody);
    }

    @Then("{word} send a {httpMethod} request to {string}")
    public void sendRequestTo(String agentName, Method httpMethod, String url) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) AgentManager.getInstance().getOrCreatAgent(agentName);
        final String processedUrl = ScenarioContext.getInstance().processString(url);
        webRequestAgent.buildGivenBasicRequest(processedUrl);
        webRequestAgent.makeRequest(httpMethod);
    }

    @Then("{word} should receive a response with status code {int}")
    public void verifyResponseStatusCode(String agentName, int statusCode) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) AgentManager.getInstance().getOrCreatAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("{word} receive a response with body {string}")
    public void verifyResponseBody(String agentName, String expectedBody) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) AgentManager.getInstance().getOrCreatAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        final String processedExpectedBody = ScenarioContext.getInstance().processString(expectedBody);
        assertEquals(processedExpectedBody, response.getBody().asString());
    }

    @Then("{word} receive a response with body contains {string}")
    public void verifyResponseBodyContains(String agentName, String expectedBody) {
        final WebRequestAgent webRequestAgent = (WebRequestAgent) AgentManager.getInstance().getOrCreatAgent(agentName);
        final Response response = webRequestAgent.getResponse();
        final String processedExpectedBody = ScenarioContext.getInstance().processString(expectedBody);
        assertTrue(response.getBody().asString().contains(processedExpectedBody));
    }
}
