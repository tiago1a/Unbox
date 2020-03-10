

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestApiTest {

    String url = "https://api.thecatapi.com/v1/images/search?limit=5&page=10&order=Desc";
    String bodyAut = "{\"email\": \"tiagoamaro01@gmail.com\", \"appDescription\": \"testetesteteste\"}";
    String autorizacao = "22444789-fee9-4fa4-9163-cf99c362ec2f";
    //Teste de cadastro no site

//    @Test
//    public void TestApi() {
//
//        RestAssured.given()
//                .contentType("application/json").auth().oauth2(autorizacao)
//                .when().get(url)
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    public void TestConsultaCategories() {
//
//        RestAssured.given()
//                .contentType("application/json").auth().oauth2(autorizacao)
//                .when().get("https://api.thecatapi.com/v1/categories")
//                .then()
//                .statusCode(200);
//    }
//
//    //Consulta Categorias
//    @Test
//    public void TestConsultaCategoriaEspecifica() {
//        String id = "asho";
//        RestAssured.given()
//                .contentType("application/json").auth().oauth2(autorizacao)
//                .when().get("https://api.thecatapi.com/v1/images/search?breed_"+id)
//                .then()
//                .statusCode(200);
//    }

//    //Votar
//    @Test
//    public void TesteVotarImagem() {
//        RestAssured.given()
//                .contentType("application/json").auth().oauth2(autorizacao)
//                .body("{\"image_id\": \"asf2\",\"sub_id\": \"my-user-1234\",\"value\": 1}")
//                .when().post("https://api.thecatapi.com/v1/votes")
//                .then()
//                .statusCode(200);
//    }

    //Votar
    @Test
    public void TesteVotarImagem() {

        String id = "votes";

        RestAssured.baseURI = "https://api.thecatapi.com/v1/";

        Response response = null;

        try {
            response = (Response) RestAssured.given()
                    .header("x-api-key", "DEMO-API-KEY")
                    .contentType("application/json").auth().oauth2(autorizacao)
                    .body("{\"image_id\":\"asf2\",\"sub_id\":\"my-user-1234\",\"value\":1}")
                    .pathParam("id", id)
                    .when()
                    .post("/{id}");



        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals("Response body contains SUCCESS", bodyAsString.contains("SUCCESS"), true);
    }

}



