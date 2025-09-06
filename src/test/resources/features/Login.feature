Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given user is on login page
    When user enters valid credentials
    Then user should be logged in
