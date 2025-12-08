@AccessToWebPage
Feature: The user can access the webpage
  Rule: Successful login
    Scenario: Use valid username and password
      Given the user is a registered customer
      When enters credentials to login
        | username | password     |
        | jodyex   | pwofjodyex1N_|
      Then the login to the banking page is successful

    Scenario: Use invalid username and valid password
      Given the user is a registered customer
      When enters credentials to login
        | username      | password     |
        | jodyexample   | pwofjodyex1N_|
      Then the login is failed, error invalid credentials is shown

    Scenario: Use valid username and invalid password
      Given the user is a registered customer
      When enters credentials to login
        | username | password     |
        | jodyex   | pwofjodyex   |
      Then the login is failed, error invalid credentials is shown

    Scenario: Use invalid username and invalid password
      Given the user is a registered customer
      When enters credentials to login
        | username      | password  |
        | jodyexample   | pwofjodyex|
      Then the login is failed, error invalid credentials is shown

