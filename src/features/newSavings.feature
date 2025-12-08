@AccessToNewSavingsPage
Feature: The user can access the New Savings Page
  Rule: Successful login and access to New Savings Page
    Scenario: Use valid username and password, valid saving
      Given the user is a registered customer
      And has proper funds
      When enters a valid username and password
      And after successful login, navigates to Savings-->New Savings page
      And submits 25$
      Then new saving is successful

    Scenario: Use valid username and password, invalid saving
      Given the user is a registered customer
      And has proper funds
      When enters a valid username and password
      And after successful login, navigates to Savings-->New Savings page
      And submits 20$
      Then new saving is failed, error: The initial deposit entered does not meet the minimum amount...
