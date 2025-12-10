@AccessUpdateProfilePage
Feature: The user can access the Update Profile Page
  Rule: Successful login and access to My Profile page
    Background:
      Given the user opens the DigitalBank webpage
      And accepts cookies
      And logs in with valid username and password using datatable
        | username | jodyex       |
        | password | pwofjodyex1N_|
      And is on the MyProfile page

    Scenario: Successful mobile data update
      When updates the <mobilePhoneNumber>
        | mobilePhoneNumber |
        | 123-366-3123      |
      Then update of the profile data is successful, success: Profile Updated Successfully.

    Scenario: Invalid title update
      When updates the <titleField>
        | titleField    |
        | Please Select |
      Then update of the profile data is failed,error: ! Kérjük válasszon egyet a lista elemei közül.