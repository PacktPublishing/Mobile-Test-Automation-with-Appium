Feature: iOS Sample test

  @iOS
  Scenario: Addition of two numbers in iOS app

    When I launch iOS app
    And I choose to enter "22" and "33"
    And I tap on Compute Sum
    Then I should see the result "55"