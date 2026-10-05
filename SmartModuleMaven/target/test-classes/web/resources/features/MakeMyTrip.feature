Feature: Make My Trip

  @tag1
  Scenario: Verify flight icon on the homepage
    Given the user is on the homepage
    When the user clicks on the flight icon
    Then Verifies the flight icon should be visible
    And the user should be navigated to the flight page


