
Feature: Search and place order for products
  @searchProduct
  Scenario Outline: Search for products in both home and offer page
  Given User is on GreenCart home page
  When user searched with shortname <prodName> and extracted actual name of product
  Then User searched for <prodName> shortname in offer page
  And Validate product name in offer page matches with home page.
  
  Examples:
  |prodName|
  | tom    |
  | beet   |