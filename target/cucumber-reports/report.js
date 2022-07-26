$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/resources/features/Test.feature");
formatter.feature({
  "line": 1,
  "name": "Navigate in BlankFactor",
  "description": "",
  "id": "navigate-in-blankfactor",
  "keyword": "Feature"
});
formatter.before({
  "duration": 402662500,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Technical test",
  "description": "",
  "id": "navigate-in-blankfactor;technical-test",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "We are on the BlankFactor main page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "When we go to the blog section and search for a blog",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "Validate that the page loaded is correct",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "We subscribe by entering an email",
  "keyword": "And "
});
formatter.match({
  "location": "StepTest.weAreOnTheBlankFactorMainPage()"
});
formatter.result({
  "duration": 4719905100,
  "status": "passed"
});
formatter.match({
  "location": "StepTest.whenWeGoToTheBlogSectionAndSearchForABlog()"
});
formatter.result({
  "duration": 21873049100,
  "status": "passed"
});
formatter.match({
  "location": "StepTest.validateThatThePageLoadedIsCorrect()"
});
formatter.result({
  "duration": 178343300,
  "status": "passed"
});
formatter.match({
  "location": "StepTest.weSubscribeByEnteringAnEmail()"
});
formatter.result({
  "duration": 3806242300,
  "status": "passed"
});
formatter.after({
  "duration": 4705029300,
  "status": "passed"
});
});