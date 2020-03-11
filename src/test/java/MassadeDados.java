import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MassadeDados {
	String urlBase = "https://api.thecatapi.com/v1/";
	String autorizacao = "22444789-fee9-4fa4-9163-cf99c362ec2f";
	String bodyCadastro = "{\"email\": \"tiagoamaro01@gmail.com\", \"appDescription\": \"testetesteteste\"}";
	String bodyCadastroApenasEmail = "{\"email\": \"tiagoamaro01@gmail.com\"}";
	String bodyCadastroApenasDescricao = "{\"appDescription\": \"testetesteteste\"}";
	String url = "/images/search";
	Response response = null;
	String racaGato = "asho";
	String votes = "votes";
	int idvotos = 178069;
//NAVEGACAO
	String menucadastro = "/user/passwordlesssignup";

	protected String construtorDeURL(String complemento) {
		return (RestAssured.baseURI = "https://api.thecatapi.com/v1" + complemento);
	}

}
