@tag
Feature: Purchase the order from Ecommerce application 
 
 
 

  @ErrorValidation
  Scenario Outline: Negative tests of submitting the order.
  Given: I landed on EcommercePage
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password.." message is displayed
    
     Examples: 
      | username            | password           |
      | ngill@email.com     | Patiala@12         | 