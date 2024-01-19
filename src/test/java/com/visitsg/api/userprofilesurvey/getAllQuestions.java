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
    private static String qsnId;
    private static String optionId;

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

        qsnId = jasonPath.get("data.results[5].uid");
        optionId = jasonPath.get("data.results[5].surveyOptions[5].uid");

    }

    /**
     * testing POST call
     **/

    // 1. Aa string (less than 10 lines) 2. Hashmap  (less than 20 lines + easy request body) 3.file format (other) - request body
    @Test
    public void testUpdateCompanyCommand() throws IOException {
        String requestPayload = "{\n" +
                "    \"userResponses\": [\n" +
                "        \n" +
                "        {\n" +
                "            \"questionId\": \"" + qsnId + "\",\n" +
                "            \"options\": [\n" +
                "                {\n" +
                "                \"uid\" : \"" + optionId + "\"\n" +
                "                }\n" +
                "\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"deviceToken\": \"TTT\"\n" +
                "    \n" +
                "}";

        given().
                baseUri(globalConstants.getGlobalVariables("SURVEY-URL-STG")).
                header("x-api-key", globalConstants.getGlobalVariables("SURVEY-XAPI-KEY")).
                body(requestPayload).
                when().
                post("/mobile/survey_questions/submit_user_responses").
                then().statusCode(200).body("data.result.surveyUserResponses[0].questionId", equalTo(qsnId),
                        "data.result.surveyUserResponses[0].options[0].uid", equalTo(optionId));
    }
}