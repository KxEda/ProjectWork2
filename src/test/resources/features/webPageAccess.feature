@AccessToWebPage
Feature: The user can access the webpage
  Rule: Successful login
    Background:
      Given the user is a registered customer
      And is on the Digital Bank webpage Login page

    Scenario: Use valid username and password
      When enters <userName> and <password> to login
        | userName | password     |
        | jodyex   | pwofjodyex1N_|
      Then the login to the banking page is successful

    Scenario Outline: Use invalid username and valid password
      When enters <userName> and <password> to login
      Examples:
        | userName      | password     |
        | jodyexample   | pwofjodyex1N_|
        | jodyex        | pwofjodyex   |
        | jodyexample   | pwofjodyex   |
      Then the login is failed, error invalid credentials is shown

