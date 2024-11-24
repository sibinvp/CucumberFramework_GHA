
Feature: Add and place order from CSV
 
 @placeOrder_csv 
  Scenario Outline: Place order from cart using csv
  Given User is on GreenCart home page for <TestCase>
  When user searched with prodName and click add to cart for required quantity from data sheet
  Then User searched for tomato and quantity in cart page
  And user validate apply button and place the order
	
	Examples:
  | TestCase |
  | TC2			 |
  | TC1			 |
 
  
 
