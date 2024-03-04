Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Sunday isn't Friday
    Given today is <today> and stored as var "today"
    When read from var "@today" and should told me "Nope"
    Examples:
      | today     |
      | Sunday    |
      | Saturday  |
      | Friday    |
      | Thursday  |
      | Wednesday |
      | Tuesday   |
      | Monday    |