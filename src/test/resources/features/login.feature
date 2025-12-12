@LoginAndOpenHomePage
Feature: DigitalBank login and opening Home page
  Rule: Login and check loginpage layout
  Scenario: Login
    Given the user opens the DigitalBank webpage
    And accepts cookies
    When logs in with valid username and password using datatable
      | username | jodyex       |
      | password | pwofjodyex1N_|
    Then the user is logged in successfully and can see the Home page

  Scenario: Login page layout test
    Given the user opens the DigitalBank webpage
    And accepts cookies
    Then login page layout is ok