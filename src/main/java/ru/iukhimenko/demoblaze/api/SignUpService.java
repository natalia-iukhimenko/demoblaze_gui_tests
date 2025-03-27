package ru.iukhimenko.demoblaze.api;

import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static ru.iukhimenko.demoblaze.SysConfig.CONFIG;

public class SignUpService {
    public static void signUpAs(String username, String password) {
        var requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);

        given().baseUri(CONFIG.baseUrl()).contentType("application/json")
                .body(requestBody)
                .when().post("/signup")
                .then().statusCode(200);
    }
}
