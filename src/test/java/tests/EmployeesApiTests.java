package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EmployeesApiTests extends BaseEmployeesControllerTest {

    @Test(description = "Print all employees.")
    public void printAllEmployees() {
        Response response = given()
                .log().uri()
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "Print the employee with id 2.")
    public void printEmployee() {
        Response response = given()
                .log().uri()
                .when()
                .get("/employee/{id}", "2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "Create a new employee.")
    public void createEmployee() {
        Response response = given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body("{\"employee_name\":\"Automation Test\"," +
                        "\"employee_salary\":\"123\"," +
                        "\"employee_age\":\"35\", " +
                        "\"profile_image\":\"\"}")
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "Update an existent employee.")
    public void updateEmployee() {
        Response response = given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body("{\"employee_name\":\"Automation Test\"," +
                        "\"employee_salary\":\"123\"," +
                        "\"employee_age\":\"40\", " +
                        "\"profile_image\":\"\"}")
                .when()
                .put("/update/{id}", "1")
                .then()
                .statusCode(200)
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "Delete an Employee.")
    public void deleteEmployee() {
        Response response = given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .delete("/delete/{id}", "1")
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }
}