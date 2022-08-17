package com.cme;

import com.cme.entities.User;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PalindromeResourceTest extends BaseTestHelper {

    @Test
    public void testCreatePalindromeEndpoint() {
        var request = getPalindromePayload();
        var expected = getPalindromeResponse();
        var response = given()
            .contentType(ContentType.JSON)
            .body(request)
          .when().post("/palindrome")
          .then()
             .statusCode(200)
             .body("username", notNullValue()).extract().body().as(User.class);
        assert(response.isPalindrome());
        assert(response.getUsername().equals(expected.getUsername()));
        assert(response.getValue().equalsIgnoreCase(expected.getValue()));
        assert(response.getMessage().equals(expected.getMessage()));
    }

    @Test
    public void testNotCreatePalindromeEndpoint() {
        var request = getNotPalindromePayload();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should be same as reverse of the word")));
    }

    @Test
    public void testNullCreatePalindromeEndpoint() {
        var request = getNullPalindromePayload();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should NOT be blank/null")));
    }

    @Test
    public void testEmptyCreatePalindromeEndpoint() {
        var request = getEmptyPalindromePayload();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should NOT be blank/null")));
    }

    @Test
    public void testSpacesCreatePalindromeEndpoint() {
        var request = getSpacePalindrome();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should NOT have leading/trailing/in-between spaces. Should be a single word")));
    }

    @Test
    public void testLeadSpaceCreatePalindromeEndpoint() {
        var request = getLeadSpacePalindrome();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should NOT have leading/trailing/in-between spaces. Should be a single word")));
    }

    @Test
    public void testTrailSpaceCreatePalindromeEndpoint() {
        var request = getTrailSpacePalindrome();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should NOT have leading/trailing/in-between spaces. Should be a single word")));
    }

    @Test
    public void testNumberCreatePalindromeEndpoint() {
        var request = getNumberPalindromePayload();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(400)
                .body("title", is(equalTo("Constraint Violation")))
                .body("violations[0].message", is(equalTo("palindrome should NOT be a number")));
    }

    @Test
    public void testAlphaNumericCreatePalindromeEndpoint() {
        var request = getAlphaNumbericPalindromePayload();
        var expected = getAlphaNumericPalindromeResponse();
        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/palindrome")
                .then()
                .statusCode(200)
                .body("username", notNullValue()).extract().body().as(User.class);
        assert(response.isPalindrome());
        assert(response.getValues().contains(expected.getValue()));
        assert(response.getUsername().equals(expected.getUsername()));
        assert(response.getMessage().equals(expected.getMessage()));
        assert(response.getValue().equalsIgnoreCase(expected.getValue()));
    }

    @Test
    public void testGetPalindromeEndpoint() {
        var expected = getGetPalindromeResponse();
        var response = given()
                .contentType(ContentType.JSON)
                .when().get("/palindrome/Krishna")
                .then()
                .statusCode(200)
                .body("username", notNullValue()).extract().body().as(User.class);
        assert(response.isPalindrome());
        assert(response.getValues().size() > 0);
        assert(response.getUsername().equals(expected.getUsername()));
        assert(response.getMessage() == null);
        assert(response.getValue().equalsIgnoreCase(expected.getValue()));
    }

}