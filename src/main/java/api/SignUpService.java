package api;

import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class SignUpService {
    public static void signUpAs(String username, String password) {
        var requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);

        given().baseUri("https://api.demoblaze.com").contentType("application/json")
                .body(requestBody)
                .when().post("/signup")
                .then().statusCode(200);
    }
}
