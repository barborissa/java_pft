package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  public Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Issue getAnIssue(int issueId) throws IOException {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    return issueSet.stream().findFirst().get();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    Issue issue = getAnIssue(issueId);
    if (issue.getState_name().equals("Resolved") || issue.getState_name().equals("Closed")
            || issue.getState_name().equals("Deleted")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
