package base;

import base.reporting.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiActions {

    private static ApiActions ApiActions;

    public synchronized static ApiActions get() {
        if (ApiActions == null) {
            ApiActions = new ApiActions();
        }
        return ApiActions;
    }

    /**
     * This method will convert raw files to XML
     *
     * @param response The response file that we need to convert.
     * @Author - Zann
     */

    public XmlPath rawToXML(Response response) {
        String respon = response.asString();
        XmlPath x = new XmlPath(respon);
        return x;
    }

    /**
     * This method will convert raw files to JSON
     *
     * @param response The response file that we need to convert.
     * @Author - Zann
     */

    public JsonPath rawToJson(Response response) {
        String respon = response.asString();
        JsonPath x = new JsonPath(respon);
        return x;
    }

    /**
     * This method will return SessionKey
     *
     * @param.
     * @Author - Zann
     */

    public String sessionKey() {
        RestAssured.baseURI = "https://staging.refinemirror.com";
        Response res = given().header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\"email\": \"testgetthemirror@gmail.com\",\"password\": \"Mirr0r\"}").
                when().post("/api/v1/auth/login").then().
                statusCode(200).extract().response();
        JsonPath js = rawToJson(res);
        String sessionId = js.get("data.api_token");
        //System.out.println(sessionId);
        return sessionId;
    }


    /**
     * This method will help to get the BaseURI depending on the env
     *
     * @param.
     * @Author - Zann
     */
    public String baseURIForEnv() {
        String URI = null;
        if (ExtentManager.environment.equalsIgnoreCase("E2SL")) {
            URI = "https://gcp-traveler-cpservice-sl-qa.aexp.com/cpservice/rest";
        } else if (ExtentManager.environment.equalsIgnoreCase("E2HL")) {
            URI = "https://gcp-traveler-cpservice-qa.aexp.com/cpservice/rest";
        } else {
            URI = "https://gcp-traveler-cpservice.aexp.com/cpservice/rest";
        }
        return URI;
    }


}
