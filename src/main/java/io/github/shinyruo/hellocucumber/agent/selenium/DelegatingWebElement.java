package io.github.shinyruo.hellocucumber.agent.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;
import java.util.Objects;

public class DelegatingWebElement implements WebElement, WrapsElement, Locatable {
    private final WebElement wrappedElement;

    public DelegatingWebElement(WebElement webElement) {
        this.wrappedElement = webElement;
    }

    @Override
    public void click() {
        wrappedElement.click();
    }

    @Override
    public void submit() {
        wrappedElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        wrappedElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        wrappedElement.clear();
    }

    @Override
    public String getTagName() {
        return wrappedElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return wrappedElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return wrappedElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return wrappedElement.isEnabled();
    }

    @Override
    public String getText() {
        return wrappedElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return wrappedElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return wrappedElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return wrappedElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return wrappedElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return wrappedElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return wrappedElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return wrappedElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return wrappedElement.getScreenshotAs(target);
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) wrappedElement).getCoordinates();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WebElement that)) {
            return false;
        }

        if (that instanceof WrapsElement wrappingElement) {
            that = wrappingElement.getWrappedElement();
        }

        return wrappedElement.equals(that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(wrappedElement);
    }

}
