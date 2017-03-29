$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/feature/retrieveData.feature");
formatter.feature({
  "id": "search-axs-company",
  "description": "",
  "name": "search AXS company",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "search-axs-company;search-axs-company",
  "description": "",
  "name": "search AXS company",
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
  "name": "user search \u0027AXS\u0027 company",
  "keyword": "And ",
  "line": 5
});
formatter.match({
  "location": "HomePageStepDef.Starts_WSJ_session()"
});
