package br.com.hivecloud.transportadora.transportadora;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.hivecloud.transportadora.main.Application;
import br.com.hivecloud.transportadora.web.dto.TransportadoraCreateUpdateDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransportadoraServiceIntegrationTest {

	@LocalServerPort
	int port;

	private static final String ENDPOINT = "http://localhost:%s/api/transportadoras";

	@Test
	public void givenRequestForTransportadora_findAll() {
		given().get(String.format(ENDPOINT, port)).then().assertThat().statusCode(200).body("content.size()", is(2))
				.body("nome", hasItems("paulo querioz"));

	}

	@Test
	public void givenRequestForTransportadora_whenIdEqualsOne() {
		given().get(String.format(ENDPOINT + "/1", port)).then().assertThat().statusCode(200).body("nome",
				equalTo("paulo querioz"));

	}

	@Test
	public void givenRequestForTransportadora_whenIdNotFound() {
		given().get(String.format(ENDPOINT + "/22", port)).then().assertThat().statusCode(404);

	}

	@Test
	public void givenRequestForTransportadora_filtre__whenNameReturnOneElement() {
		given().get(String.format(ENDPOINT + "/filtre?nome=paulo", port)).then().assertThat().statusCode(200)
				.body("nome", hasItems("paulo querioz"));

	}

	@Test
	public void givenRequestForTransportadora_filtre__whenNameWrongReturnZeroElement() {
		given().get(String.format(ENDPOINT + "/filtre?nome=gabriela", port)).then().assertThat().statusCode(200);

	}

	@Test
	public void givenRequestForTransportadora_whenNewTransportadoraIsCreatedWithSucess() {
		TransportadoraCreateUpdateDto dto = new TransportadoraCreateUpdateDto();
		given().post(String.format(ENDPOINT, port), dto).then().assertThat().statusCode(200);

	}

}
