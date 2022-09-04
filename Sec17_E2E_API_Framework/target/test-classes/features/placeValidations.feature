@tag
Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if Place is Added using AddPlace API
    Given AddPlace API Payload with "<name>" "<language>" "<address>"
    When User Calls "AddPlaceAPI" using "POST" http request
    Then API Call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_ID created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name | language | address     |
      | Amit | English  | 351 NS Road |
 #    | Amit1 | English1 | 351 NS Road1 |
  
  
  @DeletePlace
  Scenario: Verify if DeletePlace API is working fine
    Given DeletePlace Payload
    When User Calls "deletePlaceAPI" using "POST" http request
    Then API Call is success with status code 200
    And "status" in response body is "OK"