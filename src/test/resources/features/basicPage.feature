Feature: DigitalBank basic page opening
  Background:
    Given the user opens the DigitalBank webpage
    And logs in with valid username and password

  Scenario: Open Home page successfully
    Then the Home page is displayed
