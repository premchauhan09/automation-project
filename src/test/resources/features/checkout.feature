Feature: Checkout Functionality
  As a user
  I want to complete the checkout process
  So that I can purchase items from the cart

  Background:
    Given I am logged in as a standard user for checkout

  Scenario: Complete checkout flow
    When I add "Sauce Labs Bike Light" to the cart
    And I go to the cart
    And I proceed to checkout with details "John", "Doe", "12345"
    Then the order should be completed successfully
