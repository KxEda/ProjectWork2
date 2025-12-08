@AccessToWebPage
Feature: The user can access the webpage
  Rule: Successful login
    Scenario: Use valid username and password
      Given the user is a registered customer
      When enters a valid username
      And enters a valid password
      Then the login to the banking page is successful

    Scenario: Use invalid username and valid password
      Given the user is a registered customer
      When enters an invalid username
      And enters a valid password
      Then the login is failed, error invalid credentials is shown

    Scenario: Use valid username and invalid password
      Given the user is a registered customer
      When enters a valid username
      And enters an invalid password
      Then the login is failed, error invalid credentials is shown

    Scenario: Use invalid username and invalid password
      Given the user is a registered customer
      When enters an invalid username
      And enters an invalid password
      Then the login is failed, error invalid credentials is shown

