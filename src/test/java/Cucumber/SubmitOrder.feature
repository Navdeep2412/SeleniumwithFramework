@tag
Feature: Purchase the order from Ecommerce application 
 
 Background:
 Given: I landed on EcommercePage

  @SubmitOrder
  Scenario Outline: Positive tests of submitting the order.
    Given Logged in with username <username> and password <password>
    When I add the product <productName> from cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
    
     Examples: 
      | username            | password           | productName  |
      | ngill@email.com     | Patiala@11         | ZARA COAT 3  |