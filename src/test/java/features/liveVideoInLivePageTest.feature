@LiveVideoLivePage
Feature: test for checking that for all games in championship have a live-video in the live page

  Scenario: open main page and check all games

    Given open https://astekbet55.com
    And dismiss alert
    And Click button 'live' in top menu
    And select necessary filters: all sports, live-game
    When Get list of the championship
    Then Verify that icon for each game appears