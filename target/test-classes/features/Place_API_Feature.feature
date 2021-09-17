#Author: milap Soni
Feature: Test location API

  @Addplace
  Scenario Outline: Verify with API new place is getting add or not
    Given Payload is present with "<name>" and "<website>"
    When usercalls "AddPlaceAPI" with "POST" method
    Then API call got success with status code 200
    And verify "status" in response is "OK"
    And verify "scope" in response is "APP"
    And verify "place_id" created maps to "<name>" using "GetPlaceAPI"

    Examples: 
      | name       | website              |
      | Milap_Soni | https://google.com   |
      #| Vijay_Soni | https://facebook.com |
      
    
    
   @DeletePlace
   Scenario: Verify user can delete palce using delete API
   Given Payload is present for deleteAPI
   When usercalls "DeletePlaceAPI" with "DELETE" method
   Then API call got success with status code 200
   And verify "status" in response is "OK"
   
   
  
