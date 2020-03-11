
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.ContentType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestApiTest extends MassadeDados {
	
	@BeforeClass
	public static void inicio() {
	RestAssured.baseURI = "https://api.thecatapi.com/v1";
	}

	
	//EFETUAR CADASTRO NO SITE
	@Test
	public void TestApiNovoCadastro() {
	response=(Response)RestAssured
			.given()
			.header("x-api-key","DEMO-API-KEY")
			.contentType("application/json")
			.auth().oauth2(autorizacao)
			.body(bodyCadastro)
			.when()
			.post("https://api.thecatapi.com/v1/user/passwordlesssignup")
			.then().assertThat()
			.statusCode(200);
			System.out.println("RESULTADO NOVO CADASOTRO => " + response.asString());
	}
	
 /**VERIFICAR STATUS CODE 400**/
	/*@Test
	public void VerificarCamposObrigatorioCadastro() {
	response=(Response)RestAssured
			.given()
			.header("x-api-key","DEMO-API-KEY")
			.contentType("application/json")
			.auth().oauth2(autorizacao)
			.body(bodyCadastroApenasDescricao)
			.when()
			.post("https://api.thecatapi.com/v1/user/passwordlesssignup")
			.then().log().all().assertThat().statude(sCo400);
			System.out.println(response.getStatusCode());
			ResponseBody body = response.getBody();
			String bodyAsString = body.asString();	
	}
	*/

	//TESTE LIMITANDO A CONSULTA EM 8
	@Test
	public void TestApiPesquisaImagem() {
		RestAssured.given().queryParam("limit", 8)
		.contentType("application/json")
		.auth().oauth2(autorizacao)
		.when().get(url)
		.then().log().all().assertThat().statusCode(200);
	}

	//PESQUISAR POR UMA CATEGORIA ESPECIFICA
		@Test
		public void TestConsultaCategoriaEspecifica() {
			RestAssured.given().queryParam("limit", 8)
			.contentType("application/json")
			.auth().oauth2(autorizacao)
			.when().get("/images/search?breed_" + racaGato)
			.then().log().all().assertThat().statusCode(200);

	}

	// EFETUAR UMA VOTACAO
	@Test
	public void TesteVotarImagem() {

		try {
			response = (Response) RestAssured.given().header("x-api-key", "DEMO-API-KEY")
					.contentType("application/json").auth().oauth2(autorizacao)
					.body("{\"image_id\":\"asf2\",\"sub_id\":\"my-user-1234\",\"value\":1}")
					.pathParam("id", votes)
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
	
	
	// DELETAR UM VOTO
		@Test
		public void deletaImagem() {

			try {
				response = (Response) RestAssured.given().header("x-api-key", "DEMO-API-KEY")
						.contentType("application/json").auth().oauth2(autorizacao)
						.pathParam("idvoto", idvotos)
						.when()
						.delete("/vote/" + "idvoto");

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Response :" + response.asString());

			ResponseBody body = response.getBody();
			String bodyAsString = body.asString();
			Assert.assertEquals("Response body contains SUCCESS", bodyAsString.contains("SUCCESS"), true);
		}

}
