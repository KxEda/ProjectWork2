@AccessToNewSavingsPage
Feature: The user can access the New Savings Page
  Rule: Successful login and access to New Savings Page
    Background:
      Given the user is logged in
      And on the New Savings page

    Scenario Outline: Valid saving
      When creates a new saving Account with <accountType>, <ownership>, <accountName> and <initialDeposit>
      Examples:
        | accountType | ownership | accountName | initialDeposit |
        | Savings     | Individual| TestAccount | 25             |
        | Savings     | Individual| TestAccount | 30             |

      Then new saving is created successfully

    Scenario Outline: Invalid saving
      When creates a new saving Account with <accountType>, <ownership>, <accountName> and <initialDeposit>
      Examples:
        | accountType | ownership | accountName | initialDeposit |
        | Savings     | Individual| TestAccount | 24             |
        | Savings     | Individual| TestAccount | 0              |

      Then new saving is failed, error: The initial deposit entered does not meet the minimum amount...
