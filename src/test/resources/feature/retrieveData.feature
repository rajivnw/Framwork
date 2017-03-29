Feature: search AXS company 
Scenario: search AXS company 
	Given user starts WSJ session 
	Then user should see WSJ home page with all information 
	And user search 'AXS' company 
#	Then user retrieve 'AXS' company data