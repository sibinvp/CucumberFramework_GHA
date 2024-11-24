
Feature: Search products from CSV
 
 @searchProduct_csv 
  Scenario Outline: Search for products in both home and offer page using csv
  Given User is on GreenCart home page for <TestCase>
  When user searched with shortname and extracted actual name of product
  Then User searched for shortname in offer page
  And Validate product name in offer page matches with home page.
  
	
	Examples:
  | TestCase |
  | TC3			 |
  | TC4			|
 
  
 
