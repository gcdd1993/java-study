package io.github.gcdd1993.test;

import io.github.gcdd1993.BaeldungSpringSecurityApplication;
import io.github.gcdd1993.spring.TestIntegrationConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BaeldungSpringSecurityApplication.class, TestIntegrationConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocalizationIntegrationTest {

    @Value("${local.server.port}")
    int port;

    @BeforeEach
    public void init() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void given_theLanuageParamterIsEnglish_then_the_title_of_the_log_page_is_Login() {
        final RequestSpecification request = RestAssured.given().param("lang", "en");
        request.when().get("/login").then().assertThat().statusCode(200).and().body(containsString("<h1>Login</h1>"));
    }

    @Test
    public void given_theLanuageParamterIsSpanish_then_the_title_of_the_log_page_is_Ingreso() {
        final RequestSpecification request = RestAssured.given().param("lang", "es_ES");
        request.when().get("/login").then().assertThat().statusCode(200).and().body(containsString("<h1>Ingreso</h1>"));
    }
}
