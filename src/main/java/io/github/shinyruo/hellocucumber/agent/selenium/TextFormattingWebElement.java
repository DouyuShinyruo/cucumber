package io.github.shinyruo.hellocucumber.agent.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TextFormattingWebElement extends DelegatingWebElement {

    public TextFormattingWebElement(WebElement webElement) {
        super(webElement);
    }

    @Override
    public String getText() {
        return normalizeText(super.getText());
    }

    @Override
    public String getAttribute(String name) {
        return normalizeText(super.getAttribute(name));
    }

    @Override
    public List<WebElement> findElements(By by) {
        return super.findElements(by).stream().map(TextFormattingWebElement::new).collect(Collectors.toList());
    }

    @Override
    public WebElement findElement(By by) {
        return new TextFormattingWebElement(super.findElement(by));
    }

    private static String normalizeText(String text) {
        return text != null ? text.replaceAll("\u00A0|\\r\\n|\\r|\\n", " ").trim() : null;
    }
}
