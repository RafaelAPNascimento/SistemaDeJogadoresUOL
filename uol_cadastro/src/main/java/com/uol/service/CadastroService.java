package com.uol.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Model;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.map.ObjectMapper;

import com.uol.exceptions.CodinomeIndisponivelException;
import com.uol.service.domain.Grupo;
import com.uol.service.domain.Jogador;
import com.uol.service.domain.LigaDaJustica;
import com.uol.service.domain.Vingadores;

//classe que faz a comunicação com os web services
@Model
public class CadastroService {

	// URLs dos codinomes dos heróis
	private final String URL_VINGADORES = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	private final String URL_LIGA_JUSTICA = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	// URL da aplicação de cadastro Back End
	//private final String URL_CADASTRO_BE = "http://localhost:8080/uol_cadastro_be/jogador";
	private final String URL_CADASTRO_BE = "http://backend:8080/uol_cadastro_be/jogador";

	//busca os codinomes no web service
	public List<String> getCodinomes(Grupo grupoSelecionado) {

		if (grupoSelecionado == Grupo.VINGADORES)
			return getCodinomesVingadores();
		else
			return getCodinomesLigaJustica();
	}
	
	private List<String> getCodinomesVingadores() {

		Response response = ClientBuilder.newClient().target(URL_VINGADORES).request(MediaType.APPLICATION_JSON).get();

		if (response.getStatusInfo() == Response.Status.OK) {

			try {
				String codinomesJson = response.readEntity(String.class);
				ObjectMapper mapper = new ObjectMapper();
				Vingadores vingadores = mapper.readValue(codinomesJson.getBytes(), Vingadores.class);

				List<String> codinomes = vingadores.getVingadores().stream().map(v -> v.getCodinome())
						.collect(Collectors.toList());
				return codinomes;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private List<String> getCodinomesLigaJustica() {

		Response response = ClientBuilder.newClient().target(URL_LIGA_JUSTICA).request(MediaType.APPLICATION_XML).get();

		if (response.getStatusInfo() == Response.Status.OK) {
			
			try {
				String codinomesXml = response.readEntity(String.class);
				JAXBContext jaxbContext = JAXBContext.newInstance(LigaDaJustica.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				LigaDaJustica codinomes = (LigaDaJustica) jaxbUnmarshaller
						.unmarshal(new ByteArrayInputStream(codinomesXml.getBytes()));
				return codinomes.getCodinomes();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//salva ou atualiza o jogador
	public Jogador cadastrarJogador(Jogador jogador) {

		final String operacao;
		
		if(jogador.getId() == null)
			operacao = "/save";
		else
			operacao = "/update";
		
		Entity<Jogador> data = Entity.entity(jogador, MediaType.APPLICATION_XML_TYPE);
		Builder builder = ClientBuilder.newClient().target(URL_CADASTRO_BE + operacao)
															.request(MediaType.APPLICATION_XML);
		
		Response response = null;
		
		//se vai salvar, utiliza método POST
		if(jogador.getId() == null)
			response = builder.post(data, Response.class);

		//senão vai salvar, utiliza método PUT
		else
			response = builder.put(data, Response.class);
		
		if(response.getStatusInfo() == Response.Status.OK) {			
			jogador = response.readEntity(Jogador.class);
			return jogador;
		}
		else if(response.getStatusInfo() == Response.Status.BAD_REQUEST) {
			//status 4** erro da regra de negócio
			String message = response.readEntity(String.class);
			throw new CodinomeIndisponivelException(message);
		}
		else {
			//status 5** qualquer outro erro interno no web service
			System.out.println(response.getStatusInfo());
			String message = response.readEntity(String.class);
			throw new RuntimeException(message);
		}			
	}

	public List<Jogador> getJogadores() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL_CADASTRO_BE + "/all");

		List<Jogador> jogadores = target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Jogador>>() {});

		return jogadores;
	}

	public boolean excluirJogador(Jogador jogador) {
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(URL_CADASTRO_BE + "/delete")
								.path(jogador.getId().toString())
								.request()
								.delete();
		
		
		return (response.getStatusInfo() == Response.Status.OK);
			
	}

}
