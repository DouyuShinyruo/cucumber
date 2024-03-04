package io.github.shinyruo.hellocucumber.agent;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class WebRequestAgent extends AbstractAgent {
    private RequestSpecification request;

    @Setter
    @Getter
    private Response response;

    public WebRequestAgent(String agentName, Properties properties) {
        super(agentName, properties);
        this.request = null;
        this.response = null;
    }

    public void buildGivenBasicRequest(String baseUri) {
        this.request = given().baseUri(baseUri);
    }


    public void setBody(String body) {
        this.request.body(body);
    }

    public void setHeader(String header, String value) {
        this.request.header(header, value);
    }

    public void makeRequest(Method method) {
        if (this.request == null) {
            throw new IllegalStateException("Request is not set");
        }
        setResponse(executeRequest(this.request, method));
    }

    private Response executeRequest(RequestSpecification request, Method method) {
        return switch (method) {
            case GET -> request.get();
            case POST -> request.post();
            case PUT -> request.put();
            case DELETE -> request.delete();
            case PATCH -> request.patch();
            case OPTIONS -> request.options();
            case HEAD -> request.head();
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        };
    }
}
