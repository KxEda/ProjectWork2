@AccessToViewCheckingPage
Feature: The user can access the View Checking Page
  Rule: Successful login and access to View Checking Page
    Scenario: Use valid username and password, existing former transactions
      Given the user is a registered customer
      And there are existing former transactions
      When enters a valid username and password
      And after successful login, navigates to Checking-->View Checking page
      Then the table with the former transactions listed is present

    Scenario: Use valid username and password, no existing former transaction
      Given the user is a registered customer
      And there is no existing former transaction
      When enters a valid username and password
      And after successful login, navigates to Checking-->View Checking page
      Then the table with the former transactions listed is empty

    Scenario: Use valid username and password, navigate to wrong subpage
      Given the user is a registered customer
      And there is no existing former transaction
      When enters a valid username and password
      And after successful login, navigates to Checking-->New Checking page
      Then the table with the former transactions listed is not present, but the New Transaction page is

