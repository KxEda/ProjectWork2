@AccessUpdateProfilePage
Feature: The user can access the Update Profile Page
  Rule: Successful login and access to My Profile page
    Scenario: Use valid username and password, successful mobile data update
      Given the user is a registered customer
      When enters a valid username and password
      And after successful login, navigates to My Profile page
      And updates the mobile phone number
        | mobilePhoneNumberUpdate |
        | 123-366-3123            |
      Then update of the profile data is successful, success: Profile Updated Successfully.

    Scenario: Use valid username and password, invalid title update
      Given the user is a registered customer
      When enters a valid username and password
      And after successful login, navigates to My Profile page
      And updates the title field
        | titleFieldUpdate |
        | Please Select    |
      And clicks on submit
      Then update of the profile data is failed,error: ! Kérjük válasszon egyet a lista elemei közül.