Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Sunday isn't Friday
    Given today is <today>
    When I ask whether it's Friday yet
    Then I should be told "Nope"
    Examples:
      | today     |
      | Sunday    |
      | Saturday  |
      | Friday    |
      | Thursday  |
      | Wednesday |
      | Tuesday   |
      | Monday    |