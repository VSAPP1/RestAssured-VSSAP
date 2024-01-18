package com.visitsg.api.userprofilesurvey;

import com.visitsg.api.utilities.globalConstants;
import com.visitsg.api.utilities.httpStatus.*;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


import java.io.IOException;

import static io.restassured.RestAssured.given;

public class getAllQuestions {

    /**
     * testing GET call
     **/

    @Test
    public void testGetAllQuestions() throws IOException {
        given().
                baseUri(globalConstants.getGlobalVariables("SURVEY-URL-STG")).
                header("x-api-key", globalConstants.getGlobalVariables("SURVEY-XAPI-KEY")).
                when().
                get("/web/survey_questions")
                .then().statusCode(HttpStatusCodes.OK.getCode()).body("data.results", notNullValue()).log().all();

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