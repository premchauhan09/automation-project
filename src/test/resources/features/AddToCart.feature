Feature: Add to cart functionality

  Scenario: Add and remove product from cart
    Given user is logged in with username "standard_user" and password "secret_sauce"
    When user adds a backpack to the cart
    Then cart badge count should be "1"
    When user removes the backpack from the cart
    Then cart badge count should be "0"
    Then close the browser
