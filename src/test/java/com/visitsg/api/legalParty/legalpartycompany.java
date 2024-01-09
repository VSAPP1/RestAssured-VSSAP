package com.visitsg.api.legalParty;

import com.visitsg.api.utilities.globalConstants;
import org.junit.Test;


import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class legalpartycompany {
    String companyID = "C-00089006";

    /**testing GET call**/

    @Test
    public void testCompanySearch() throws IOException {
        given().
                baseUri(globalConstants.getGlobalVariables("DOMAIN")).
                proxy(globalConstants.getGlobalVariables("PROXY"), Integer.parseInt(globalConstants.getGlobalVariables("PORT"))).
                header("Accept", globalConstants.getGlobalHeaders().get("Accept")).
                header("content-type", globalConstants.getGlobalHeaders().get("content-type")).
                header("UserSecurityToken", globalConstants.getSessionHeaders().get("UserSecurityToken")).
                param("companyId", companyID).
                when().
                get("/legalparty/query/companySearchQuery")
                .then().statusCode(200).body("DTYPE", equalTo("CompanySearchQueryResult"),
                        "entryCollection[0].DTYPE", equalTo("CompanySearchItem"),
                        "entryCollection[0].companyName", notNullValue(),
                        "entryCollection[0].companyId", equalTo(companyID));

    }

    /**testing POST call**/
    @Test
    public void testUpdateCompanyCommand() throws IOException {
        HashMap<String, Object> requestBody1 = new HashMap<String, Object>();
        HashMap<String, Object> requestBody2 = new HashMap<String, Object>();

        requestBody2.put("DTYPE", "UpdateCompanyData");
        requestBody2.put("companyName", globalConstants.getRandomName());
        requestBody2.put("contactEmail", globalConstants.getRandomEmail());
        requestBody2.put("contactPhone", globalConstants.getRandomContactPhoneNumber());

        requestBody1.put("DTYPE", "UpdateCompanyCommand");
        requestBody1.put("companyId", companyID);
        requestBody1.put("updateCompanyData", requestBody2);

        given().
                baseUri(globalConstants.getGlobalVariables("DOMAIN")).
                proxy(globalConstants.getGlobalVariables("PROXY"), Integer.parseInt(globalConstants.getGlobalVariables("PORT"))).
                header("Accept", globalConstants.getGlobalHeaders().get("Accept")).
                header("content-type", globalConstants.getGlobalHeaders().get("content-type")).
                header("UserSecurityToken", globalConstants.getSessionHeaders().get("UserSecurityToken")).
                body(requestBody1).
                when().
                post("/legalparty/command/updateCompanyCommand").
                then().statusCode(201).body("DTYPE", equalTo("CompanyIdCommandResult"),
                        "actionUid", notNullValue(), "identifier", equalTo(companyID));
    }
}