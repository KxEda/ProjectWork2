@AccessToTransferBetweenAccountsPage
Feature: The user can access the Transfer Between Accounts page
  Rule: Successful login and access to transfer between accounts
    Scenario: Use valid username and password, valid transfer
      Given the user is a registered customer
      And has multiple existing Accounts
      And has proper funds
      When enters a valid username and password
      And after successful login, navigates to Transfer Between Accounts page
      And set the Joint Checking Account as From Account
      And set the Individual Savings Account as To Account
      And submits 25$
      Then transfer is successful

    Scenario: Use valid username and password, invalid transfer
      Given the user is a registered customer
      And has multiple existing Accounts
      And has proper funds
      When enters a valid username and password
      And after successful login, navigates to Transfer Between Accounts page
      And set the Joint Checking Account as From Account
      And set the Joint Checking Account as To Account
      And submits 25$
      Then transfer is failed, error: Can not trasnsfer from and to the same account.