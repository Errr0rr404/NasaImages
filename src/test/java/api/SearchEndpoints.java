package api;

import base.ExtentTestManager;
import base.SetupFactory;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class SearchEndpoints extends SetupFactory {
    private static SearchEndpoints SearchEndpoints;
    String BaseURI = RestAssured.baseURI = "https://images-api.nasa.gov";
    Logger logger = Logger.getLogger(this.getClass());

    public synchronized static SearchEndpoints get() {
        if (SearchEndpoints == null) {
            SearchEndpoints = new SearchEndpoints();
        }
        return SearchEndpoints;
    }

    /**
     * This method will convert raw files to JSON
     *
     * @param response The response file that we need to convert.
     * @Author - Errr0r404
     */

    public JsonPath rawToJson(Response response) {
        String respon = response.asString();
        JsonPath x = new JsonPath(respon);
        return x;
    }

    public void testQ() {
        Response response = given()
                .param("q", "apollo 11")
                .when().get("/search")
                .then().extract().response();

        //Complete response
        JsonPath jsPath = rawToJson(response);
        String responseBody = jsPath.get("").toString();
        logger.info(responseBody + " verification completed");

        //status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        ExtentTestManager.log("statusCode " + statusCode + " : verification completed", this.getClass());

        //header
        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            ExtentTestManager.log(header.getName() + " : " + header.getValue() + ": verification completed", this.getClass());
        }
    }

    public void yearStart() {
        Response response = given()
                .param("year_start", "2018")
                .when().get("/search")
                .then().extract().response();

        //Complete response
        JsonPath jsPath = rawToJson(response);
        String responseBody = jsPath.get("").toString();
        logger.info(responseBody + " verification completed");

        //status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        ExtentTestManager.log("statusCode " + statusCode + " : verification completed", this.getClass());

        //header
        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            ExtentTestManager.log(header.getName() + " : " + header.getValue() + ": verification completed", this.getClass());
        }

    }

    public void mediaType() {
        Response response = given().param("media_type", "image")
                .when().get("/search")
                .then().extract().response();

        //Complete response
        JsonPath jsPath = rawToJson(response);
        String responseBody = jsPath.get("").toString();
        logger.info(responseBody + " verification completed");

        //status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        ExtentTestManager.log("statusCode " + statusCode + " : Assertion completed", this.getClass());

        //header
        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            ExtentTestManager.log(header.getName() + " : " + header.getValue(), this.getClass());
        }
    }

    public void yearEnd() {
        Response response = given()
                .param("year_end", "2000")
                .when().get("/search")
                .then().extract().response();
        //Complete response
        JsonPath jsPath = rawToJson(response);
        String responseBody = jsPath.get("").toString();
        logger.info(responseBody + " verification completed");

        //status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        ExtentTestManager.log("statusCode " + statusCode + " : verification completed", this.getClass());

        //header
        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            ExtentTestManager.log(header.getName() + " : " + header.getValue() + ": verification completed", this.getClass());
        }
    }


}