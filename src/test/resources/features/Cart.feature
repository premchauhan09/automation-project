Feature: Cart Functionality
  As a user
  I want to add and remove products from the cart
  So that I can manage my shopping cart effectively

  Background:
    Given I am logged in as a standard user for cart

  Scenario: Add and remove product from cart
    When I add the Sauce Labs Backpack to the cart
    Then the product should be visible in the cart
    When I remove the Sauce Labs Backpack from the cart
    Then the product should not be visible in the cart
