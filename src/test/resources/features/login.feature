@LoginAndOpenHomePage
Feature: DigitalBank login and opening Home page
  Scenario:
    Given the user opens the DigitalBank webpage
    And accepts cookies
    When logs in with valid username and password using datatable
      | username | jodyex       |
      | password | pwofjodyex1N_|

    Then the Home page is displayed