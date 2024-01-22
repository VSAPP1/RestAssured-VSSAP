package com.visitsg.api.utilities;

import com.github.javafaker.Faker;
//import com.visitsg.api.authentication.authenticationCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class globalConstants {

    private static String configsPath = "src/test/resources/configs/configuration.properties";

//    public static Map getGlobalHeaders() {
//        return Map.of("Accept", "application/json", "content-type", "application/json");
//    }

//    public static Map getSessionHeaders() throws IOException {
//        return Map.of("UserSecurityToken", authenticationCommand.getAuthToken());
//    }
//
    public static String getGlobalVariables(String key) throws java.io.IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(configsPath);
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public static String getRandomName() {
        Faker fakerNameTool = new Faker();
        return fakerNameTool.superhero().name();
    }

    public static String getRandomEmail() {
        Faker fakerNameTool = new Faker();
        return fakerNameTool.artist().name() + "@gmail.com";
    }

    public static String getRandomContactPhoneNumber() {
        Faker fakerNameTool = new Faker();
        return fakerNameTool.phoneNumber().phoneNumber();
    }
}