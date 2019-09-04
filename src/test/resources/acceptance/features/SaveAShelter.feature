Feature: Save a shelter

  As a driver, I want to save the shelter I made with my friends so that other peoples can book the same shelter

  Scenario Outline: Save a shelter
    When <userName> creates the shelter: <destination>
    Then The shelter <userName> is created with <destination> as owner
    Examples:
      | userName | destination |
      | Dakar    | 3           |


  Scenario Outline: Search all the shelters that I contributed to
    Given <userName> participated to the creation of one shelter
    When <userName> search his shelters
    Then <userName> gets all his shelters
    Examples:
      | userName | destination |
      | Dakar    | Vietnam     |

  Scenario Outline: Search all shelters request that matches the destination you've been to and suggest your shelter
    Given <userName> participated to the creation of one shelter
    When <userName> search his shelters
    Then <userName> gets all his shelters
    Examples:
      | userName | destination |
      | Dakar    | Vietnam     |
    
