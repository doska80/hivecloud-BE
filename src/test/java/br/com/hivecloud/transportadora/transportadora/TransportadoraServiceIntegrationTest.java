package br.com.hivecloud.transportadora.transportadora;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hivecloud.transportadora.main.Application;
import br.com.hivecloud.transportadora.web.dto.ModalDto;
import br.com.hivecloud.transportadora.web.dto.TransportadoraCreateUpdateDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransportadoraServiceIntegrationTest {

	@LocalServerPort
	int port;

	@Autowired
	private MockMvc mvc;

	private static final String ENDPOINT = "http://localhost:%s/api/transportadoras";

	@Test
	public void givenRequestForTransportadora_findAll() {
		given().get(String.format(ENDPOINT, port)).then().assertThat().statusCode(200).body("content.size()", is(4))
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
		given().get(String.format(ENDPOINT + "/filtre?nome=verdun", port)).then().assertThat().statusCode(200)
				.body("empresa", hasItems("verdun"));

	}

	@Test
	public void givenRequestForTransportadora_filtre__whenNameWrongReturnZeroElement() {
		given().get(String.format(ENDPOINT + "/filtre?nome=gabriela", port)).then().assertThat().statusCode(200);

	}

	@Test
	public void givenRequestForTransportadora_whenNewTransportadoraIsCreatedWithSucess() throws Exception {
		TransportadoraCreateUpdateDto dto = new TransportadoraCreateUpdateDto();
		dto.setBairro("boa viagem");
		dto.setCelular("81 999 4343 69");
		dto.setCep("51020-010");
		dto.setCidade("cidade");
		dto.setCnpj("123123");
		dto.setEmail("aa@aa.com");
		dto.setEmpresa("empresa");
		dto.setLogo("logo");
		List<ModalDto> modais = new ArrayList<>();
		ModalDto mDto = new ModalDto();
		mDto.setIdModal(3L);
		mDto.setNome("Aguaviario");
		modais.add(mDto);
		dto.setModais(modais);
		dto.setNome("nome");
		dto.setNumero("1233");
		dto.setRua("rua");
		dto.setTelefone("81 9999 4343 69");
		dto.setTermo(true);
		dto.setUf("PE");
		dto.setWhatsapp("81 999 323 23");

		mvc.perform(MockMvcRequestBuilders.post("/api/transportadoras")
				.content(asJsonString(dto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
