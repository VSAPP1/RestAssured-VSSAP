package com.visitsg.api.authentication;

import com.visitsg.api.utilities.globalConstants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class authenticationCommand {

    private static File payloadPath = new File("src/test/resources/jsonPayloads/authenticationPayload.json");

    public static String getAuthToken() throws IOException {
        Response res = given().
                baseUri(globalConstants.getGlobalVariables("DOMAIN")).
                proxy(globalConstants.getGlobalVariables("PROXY"), Integer.parseInt(globalConstants.getGlobalVariables("PORT"))).
                header("Accept", globalConstants.getGlobalHeaders().get("Accept")).
                header("content-type", globalConstants.getGlobalHeaders().get("content-type")).
                body(payloadPath).
                when().
                post("/authentication/command/authenticationCommand")
                .then().extract().response();
        if (res.statusCode() != 201) {
         throw new RuntimeException("Environment is down!");
        }
        else return JsonPath.from(res.body().asString()).getString("sessionToken");
    }
}
