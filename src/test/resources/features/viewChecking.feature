@AccessToViewCheckingPage
Feature: The user can access the View Checking Page
  Rule: Successful login and access to View Checking Page
    Background:
      Given the user is logged in
      And is on View Checking page

    Scenario: Existing former transactions
      When there are former transactions existing
      Then the table with the former transactions listed is present

    Scenario: No existing former transaction, empty list shown
      When there is no former transaction existing
      Then the table with the former transactions listed is empty

    Scenario: No existing former transaction, navigating to New Transaction
      When there is no existing former transaction
      And user chooses to continue
      Then the user is navigated to the New Transaction page

