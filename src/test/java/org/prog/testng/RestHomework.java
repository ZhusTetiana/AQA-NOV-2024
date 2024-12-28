package org.prog.testng;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.selenium.dto.LocationDto;
import org.prog.selenium.dto.PersonDto;
import org.prog.selenium.dto.ResultsDto;
import org.prog.selenium.dto.TimezoneDto;
import org.testng.Assert;
import org.testng.annotations.Test;

// TODO: write simple test that will:
// TODO: - include location to query params
// TODO: - assert location.city != null
// TODO: - print timezone.description
public class RestHomework {
    @Test
    public void myRestTest() {

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.queryParam("inc", "gender,name,nat,location");
        requestSpecification.queryParam("noinfo");
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api/");

        Response response = requestSpecification.get();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.contentType(ContentType.JSON);
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultsDto apiResponse = objectMapper.readValue(response.getBody().asString(), ResultsDto.class);

            PersonDto personDto = apiResponse.getResults().get(0);
            LocationDto location = personDto.getLocation();

            TimezoneDto timezone = personDto.getLocation().getTimezone();
            String timezoneDescription = timezone.getDescription();

            System.out.println("Timezone description: " + timezoneDescription);

            Assert.assertNotNull(location.getCity(), "City should not be null");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
