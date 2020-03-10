@TestAstekbetLiveVideo
Feature: test for checking that for all games in championship have a live-video in the main page

  Scenario: open main page and check all games

    #actions at first page
    Given open https://astekbet55.com
    And dismiss alert
    And select necessary filters: all sports, live-game
    When Get list of the championship
    Then Verify that icon for each game appears