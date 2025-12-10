Feature: The user can access the webpage
  Background:
    Given the user opens the DigitalBank webpage
    And accepts cookies

  @WebPageAccess
  Rule: Successful login
  Scenario: Successful login
    When logs in with valid username and password using datatable
      | username | jodyex       |
      | password | pwofjodyex1N_|
    Then the user is logged in successfully and can see the Home page

  Rule: Unsuccessful login
    Scenario Outline: Use invalid username and valid password
      When enters invalid "<username>" and "<password>" to login
      Then the login is failed, error invalid credentials is shown
      Examples:
      |username   |password     |
      |jodyexample|pwofjodyex1N_|
      |jodyex     |pwofjodyex   |
      |jodyexample|pwofjodyex   |
