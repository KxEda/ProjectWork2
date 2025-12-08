Feature: DigitalBank basic page opening
  Background:
    Given the user opens the DigitalBank webpage
    And logs in with valid username and password
      | username | password     |
      | jodyex   | pwofjodyex1N_|

  Scenario: Open Home page successfully
    Then the Home page is displayed
