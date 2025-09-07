Feature: Logout functionality

  Scenario: Successful logout
    Given user has logged in with username "standard_user" and password "secret_sauce"
    When user clicks on logout
    Then user should be redirected back to login page
