Feature: An example for selenium

  Scenario: The example for selenium
    When MySeleniumAgent has setup a driver for chrome
    Then MySeleniumAgent guide to website "$seleniumDemoUrl"
    Then MySeleniumAgent get title of the page
    Then MySeleniumAgent was quit
