
Feature: Search and place order
 
 @placeOrder
  Scenario Outline: Place order from cart
  Given User is on GreenCart home page
  When user searched with <prodName> and click add to cart for <quantity> quantity
  Then User searched for <prodName> and quantity <quantity> in cart page
  And user validate apply button and place the order

  Examples:
  | prodName | quantity |
  | tomato	 | 4			|
  #| cucumber | 1 				|
  
 
