package io.github.shinyruo.hellocucumber.agent;

import io.restassured.specification.RequestSpecification;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class WebRequestAgent extends AbstractAgent {

    public WebRequestAgent(String agentName, Properties properties) {
        super(agentName, properties);
    }


    private RequestSpecification givenBasicRequest() {
//        return given().baseUri(properties.getProperty("baseUri"))
//                .basePath(properties.getProperty("basePath"))
//                .port(Integer.parseInt(properties.getProperty("port", "80")));
    }
}
