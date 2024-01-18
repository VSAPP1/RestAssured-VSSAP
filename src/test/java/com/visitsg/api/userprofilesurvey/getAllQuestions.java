package com.visitsg.api.userprofilesurvey;

import com.visitsg.api.utilities.globalConstants;
import com.visitsg.api.utilities.httpStatus;
import com.visitsg.api.utilities.httpStatus.*;
import io.restassured.path.json.JsonPath;
import org.junit.Test;


import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class getAllQuestions {
    private String qsnId;
    private String optionId;

    /**
     * testing GET call
     **/

    @Test
    public void testGetAllQuestions() throws IOException {
        JsonPath jasonPath = given().
                baseUri(globalConstants.getGlobalVariables("SURVEY-URL-STG")).
                header("x-api-key", globalConstants.getGlobalVariables("SURVEY-XAPI-KEY")).
                when().
                get("/web/survey_questions")
                .then().statusCode(HttpStatusCodes.OK.getCode()).body("data.results", notNullValue(),
                        "data.results[0].text", equalTo("Where are you from?"),
                        "data.results[3].text", equalTo("Which age group do you belong to?"),
                        "data.results[4].text", equalTo("How often do you travel?"),
                        "data.results[5].text", equalTo("What are your interests?"),
                        "data.results[6].text", equalTo("Who do you usually travel with?"),
                        "data.results[7].text", equalTo("What is your usual purpose of visit?")).extract().jsonPath();

        this.qsnId = jasonPath.get("data.results[3].uid");
        this.optionId = jasonPath.get("data.results[3].surveyOptions[2].uid");
    }

    /**testing POST call**/

//    @Test
//    public void testUpdateCompanyCommand() throws IOException {
//        HashMap<String, Object> requestBody1 = new HashMap<String, Object>();
//        HashMap<String, Object> requestBody2 = new HashMap<String, Object>();
//
//        requestBody2.put("DTYPE", "UpdateCompanyData");
//        requestBody2.put("companyName", globalConstants.getRandomName());
//        requestBody2.put("contactEmail", globalConstants.getRandomEmail());
//        requestBody2.put("contactPhone", globalConstants.getRandomContactPhoneNumber());
//
//        requestBody1.put("DTYPE", "UpdateCompanyCommand");
//        requestBody1.put("companyId", companyID);
//        requestBody1.put("updateCompanyData", requestBody2);
//
//        given().
//                baseUri(globalConstants.getGlobalVariables("SURVEY-URL-STG")).
//                proxy(globalConstants.getGlobalVariables("PROXY"), Integer.parseInt(globalConstants.getGlobalVariables("PORT"))).
//                header("Accept", globalConstants.getGlobalHeaders().get("Accept")).
//                header("content-type", globalConstants.getGlobalHeaders().get("content-type")).
//                header("UserSecurityToken", globalConstants.getSessionHeaders().get("UserSecurityToken")).
//                body(requestBody1).
//                when().
//                post("/legalparty/command/updateCompanyCommand").
//                then().statusCode(201).body("DTYPE", equalTo("CompanyIdCommandResult"),
//                        "actionUid", notNullValue(), "identifier", equalTo(companyID));
//    }
}