Feature: test for checking that for all games in championship have a live-video in the main and live page

  Scenario: open main page and check all games
    Given open astekbet site
    And dismiss alert
    And select necessary filters in main page: all sports, live-game
    When List of the championship is not null
    Then Verify that icon for each game appears

  Scenario: open live page and check all games
    Given open astekbet site
    And dismiss alert
    And Click button 'live' in top menu
    And select necessary filters in live page: all sports, live-game
    When List of the championship is not null
    Then Verify that icon for each game appears