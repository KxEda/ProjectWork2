@LoginAndOpenHomePage
Feature: DigitalBank login and opening Home page
  Scenario:
    Given the user opens the DigitalBank webpage
    And logs in with valid username and password using datatable
      | username | password     |
      | jodyex   | pwofjodyex1N_|
    Then the Home page is displayed
