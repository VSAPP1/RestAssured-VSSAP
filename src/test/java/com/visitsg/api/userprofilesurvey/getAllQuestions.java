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
                baseUri(globalConstants.getGlobalVariables("SURVEY-URL-PRD")).
                header("x-api-key", globalConstants.getGlobalVariables("SURVEY-XAPI-KEY")).
                when().
                get("/web/survey_questions")
                .then().statusCode(HttpStatusCodes.OK.getCode()).body("data.results", notNullValue(),
                        "data.results[0].text", equalTo("Where are you from?"),
                        "data.results[1].text", equalTo("Which age group do you belong to?"),
                        "data.results[2].text", equalTo("What are your interests?"),
                        "data.results[3].text", equalTo("Who do you usually travel with?"),
                        "data.results[4].text", equalTo("What is your usual purpose of visit?")).extract().jsonPath();

        qsnId = jasonPath.get("data.results[2].uid");
        optionId = jasonPath.get("data.results[2].surveyOptions[5].uid");

    }

    /**
     * testing POST call
     **/

    // 1. As string (less than 10 lines) 2. Hashmap  (less than 20 lines + easy request body) 3.file format (other) - request body
    @Test
    public void testSubmitResponse() throws IOException {
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
                baseUri(globalConstants.getGlobalVariables("SURVEY-URL-PRD")).
                header("x-api-key", globalConstants.getGlobalVariables("SURVEY-XAPI-KEY")).
                body(requestPayload).
                when().
                post("/mobile/survey_questions/submit_user_responses").
                then().statusCode(200).body("data.result.surveyUserResponses[2].questionId", equalTo(qsnId),
                        "data.result.surveyUserResponses[2].options[5].uid", equalTo(optionId));
    }
}