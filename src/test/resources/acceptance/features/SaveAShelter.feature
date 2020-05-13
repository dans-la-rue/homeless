Feature: Save a shelter

  As an owner, I want to save a shelter

  Scenario Outline: Save a shelter
    When <shelterId> creates the shelter: <address>
    Then The shelter <shelterId> is created with <address> as address
    Examples:
      | shelterId | address |
      | dreaser   | 3       |

  Scenario Outline: Search all the shelters that I contributed to
    Given <userName> participated to the creation of one shelter
    When <userName> search his shelters
    Then <userName> gets all his shelters
    Examples:
      | userName |
      | dreaser  |

  Scenario Outline: Search all shelters that matches the address
    Given <userName> participated to the creation of one shelter
    When <userName> search his shelters
    Then <userName> gets his shelters
    Examples:
      | userName |
      | dreaser  |
    
