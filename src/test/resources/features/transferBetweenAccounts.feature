@AccessToTransferBetweenAccountsPage
Feature: The user can access the Transfer Between Accounts page
  Rule: Successful login and access to transfer between accounts
    Background:
      Given the user is logged in
      And has multiple existing Accounts
      And on the Transfer Between Accounts page

    Scenario: Valid transfer
      When sets the transfer with the <fromAccount>, <toAccount>, <transferAmount>
        | fromAccount    | toAccount          | transferAmount |
        | Joint Checking | Individual Savings | 25             |
      Then transfer is successful

    Scenario: Invalid transfer
      When sets the transfer with the <fromAccount>, <toAccount>, <transferAmount>
        | fromAccount    | toAccount          | transferAmount |
        | Joint Checking | Joint Checking     | 25             |
      Then transfer is failed, error: Can not trasnsfer from and to the same account.