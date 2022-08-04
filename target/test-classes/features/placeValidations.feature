Feature: Validating Place API

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI

Given Add Place Payload with "<name>" "<address>" "<language>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify palce_Id created maps to "<name>" using "getPlaceAPI"


Examples:
         | name  | address     | language |
         | Rohit | Vikas nagar | Spanish  |
         

@DeletePlace
Scenario: Verify if Delete Place functionality is working

Given DeletePlace Payload
When user calls "deletePlaceAPI" with "Post" http request
Then the API call got success with status code 200
And the "status" in response body is "OK"
         
         
  