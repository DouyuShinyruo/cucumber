package io.github.shinyruo.hellocucumber;

import com.google.inject.Guice;
import io.github.shinyruo.hellocucumber.fw.GuiceModule;
import io.github.shinyruo.hellocucumber.fw.RunCucumberTest;
import org.testng.TestNG;

public class Application {
    public static void main(String[] args) {
        Guice.createInjector(new GuiceModule());
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{RunCucumberTest.class});
        testng.run();
    }
}