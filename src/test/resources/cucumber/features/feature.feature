Feature: feature
  Description of feature

  Background: some actions before each scenarios in feature file
    Given say "Hello World"

  Scenario: simpleScenario
    Given create user with name "userName" and password "userPassword"
    When change created user name to "newUserName"
    And change created user password to "newUserPassword"
    Then check that user has name "newUserName" and password "newUserPassword"


  Scenario Outline: scenarioOutline
    Given create user via DataTable
      | name   | password   |
      | <name> | <password> |
    When change created user name to "<newName>"
    And change created user password to "<newPassword>"
    Then check that user has name "<newName>" and password "<newPassword>"

    Examples:
      | name | password | newName | newPassword |
      | test | test     | test1   | test1       |
      | test | test1    | test2   | test2       |
      | test | test2    | test3   | test3       |
      | test | test3    | test4   | test4       |
