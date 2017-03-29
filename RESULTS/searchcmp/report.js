$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/feature/searchcmp.feature");
formatter.feature({
  "id": "search-aig-company",
  "description": "",
  "name": "search AIG company",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "search-aig-company;search-aig-company",
  "description": "",
  "name": "search AIG company",
  "keyword": "Scenario",
  "line": 2,
  "type": "scenario"
});
formatter.step({
  "name": "user starts WSJ session",
  "keyword": "Given ",
  "line": 3
});
formatter.step({
  "name": "user should see WSJ home page with all information",
  "keyword": "Then ",
  "line": 4
});
formatter.step({
  "name": "user search \u0027AIG\u0027 company",
  "keyword": "And ",
  "line": 5
});
formatter.match({
  "location": "HomePageStepDef.Starts_WSJ_session()"
});
formatter.result({
  "duration": 5727897169,
  "status": "failed",
  "error_message": "java.lang.NullPointerException\r\n\tat pageTests.TestBaseProvider.getDriver(TestBaseProvider.java:14)\r\n\tat pageTests.TestMethod.openWebsite(TestMethod.java:71)\r\n\tat desktop.HomePageStepDef.Starts_WSJ_session(HomePageStepDef.java:12)\r\n\tat ✽.Given user starts WSJ session(src/test/resources/feature/searchcmp.feature:3)\r\n"
});
formatter.match({
  "location": "HomePageStepDef.ValidateWsjHomePage()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "AIG",
      "offset": 13
    }
  ],
  "location": "HomePageStepDef.SearchCompanyOnWsj(String)"
});
formatter.result({
  "status": "skipped"
});
});