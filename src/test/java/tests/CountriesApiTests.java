package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CountriesApiTests extends tests.BaseCountriesControllerTest {

    @Test(description = "Get Romania country details.")
    public void getRomaniaCountryDetailsByName() {

        Response response = given()
                .log().uri()
                .when()
                .get("/name/{name}", "rom")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name.official[0]", equalTo("Romania"))
                .body("currencies.RON.symbol[0]", equalTo("lei"))
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "This test verifies if the oficial Name for Germany is Federal Republic of Germany.")
    public void assertGermanyCountryOfficialName() {

        Response response = given()
                .when()
                .get("/name/{name}", "germany")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        String officialName = response.jsonPath().getString("[0].name.official");
        Assert.assertEquals(officialName, "Federal Republic of Germany", "The official name is not as expected.");
    }

    @Test(description = "Check if the GET request for all the countries is with success.")
    public void getAllCountries() {

        given()
                .when()
                .get("/all")
                .then()
                .statusCode(200);
    }

    @Test(description = "This test verifies if the huf currency returns the capital of Hungary: Budapest.")
    public void getBudapestCityBySearchingTheCurrency() {

        Response response = given()
                .log().uri()
                .when()
                .get("/currency/{currency}", "huf")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("capital[0][0]", equalTo("Budapest"))
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "This test verifies if the capital of Romania is Bucharest345. The test will fail.")
    public void verifyRomaniaCountryCapital() {

        Response response = given()
                .log().uri()
                .when()
                .queryParam("fullText", true)
                .get("/name/{name}", "romania")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("capital[0][0]", equalTo("Bucharest345"))
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "This test verifies the alternative spelling of Romania.")
    public void verifyAltSpellingForRomania() {

        given()
                .log().uri()
                .when()
                .queryParam("fullText", true)
                .get("/name/{name}", "romania")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("altSpellings[0]", hasItem("România"))
                .extract().response();
    }

    @Test(description = "This test verifies if the country borders for Romania are MDA, BGR, HUN, SRB and UKR.")
    public void verifyRomaniaCountryBorders() {

        List<String> expectedBorders = Arrays.asList(
                "MDA", "BGR", "HUN", "SRB", "UKR");

        List<String> actualBorders = new ArrayList<>(given()
                .log().uri()
                .when()
                .queryParam("fullText", true)
                .get("/name/{name}", "romania")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response().jsonPath()
                .getList("borders[0]", String.class));

        expectedBorders.sort(Comparator.naturalOrder());
        actualBorders.sort(Comparator.naturalOrder());

        Assert.assertEquals(expectedBorders, actualBorders);
    }

    @Test(description = "This test verifies if the official language in Romania is Romanian.")
    public void verifyRomaniaCountryLanguage() {
        Response response = given()
                .log().uri()
                .when()
                .queryParam("fullText", true)
                .get("/name/{name}", "romania")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].languages.ron", equalTo("Romanian"))
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Test(description = "This test returned the data and the response time for the get request.")
    public void getDataAndResponseTime() {
        Response response = given()
                .log().uri()
                .get("/name/{name}", "romania")
                .then()
                .statusCode(200)
                .extract().response();
        response.getBody().prettyPrint();

        String data = response.asString();

        System.out.println("Data is " + data);
        System.out.println("Response time " + response.getTime());
    }

    @Test(description = "This test verifies the Filter Response for Spain country, and prints the result.")
    public void verifyTheFilterResponseForSpain() {
        Response response = given()
                .log().uri()
                .get("/all?fields=name,capital,currencies")
                .then()
                .statusCode(200)
                .extract().response();

        String spainDetails = response.jsonPath().getString("find { it.name.common == 'Spain' }");

        System.out.println("Spain Details: " + spainDetails);

        String countryName = response.jsonPath().getString("find { it.name.common == 'Spain' }.name.common");
        String capital = response.jsonPath().getString("find { it.name.common == 'Spain' }.capital[0]");

        assertThat(countryName, is("Spain"));
        assertThat(capital, is("Madrid"));
    }

    @Test(description = "This test verifies if all the subregions for Northern Europe are included.")
    public void testGetCountriesInNorthernEurope() {

        Response response = given()
                .when()
                .get("/subregion/Northern Europe")
                .then()
                .statusCode(200)
                .extract().response();

        String[] expectedCountries = {"Denmark", "Estonia", "Finland", "Iceland", "Ireland", "Latvia", "Lithuania", "Norway", "Sweden", "United Kingdom"};

        for (String country : expectedCountries) {
            // Extract the name and capital for each country
            String countryName = response.jsonPath().getString("find { it.name.common == '" + country + "' }.name.common");
            String capital = response.jsonPath().getString("find { it.name.common == '" + country + "' }.capital[0]");

            System.out.println("Country: " + countryName + ", Capital: " + capital);

            assertThat(countryName, is(notNullValue()));
            assertThat(countryName, is(country));
            assertThat(capital, is(notNullValue()));
        }
    }
}