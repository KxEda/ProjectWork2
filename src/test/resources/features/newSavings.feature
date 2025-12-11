@AccessToNewSavingsPage
Feature: The user can access the New Savings Page
  Rule: Successful login and access to New Savings Page
    Background:
      Given the user opens the DigitalBank webpage
      And accepts cookies
      And logs in with valid username and password using datatable
        | username | jodyex       |
        | password | pwofjodyex1N_|
      And on the New Savings page

    Scenario Outline: Valid saving
      When new saving is started using "<accountType>", "<ownership>", "<accountName>", "<initialDeposit>"
      Then new saving is created successfully
      Examples:
        | accountType | ownership | accountName | initialDeposit |
        | Savings     | Individual| TestAccount | 25             |
        | Savings     | Individual| TestAccount | 30             |

    Scenario Outline: Invalid saving
      When new saving is started using "<accountType>", "<ownership>", "<accountName>", "<initialDeposit>"
      Then new saving is failed, error: The "<initialDeposit>" entered does not meet the minimum amount...
      Examples:
        | accountType | ownership | accountName | initialDeposit |
        | Savings     | Individual| TestAccount | 24             |
        | Savings     | Individual| TestAccount | 0              |
