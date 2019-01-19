package com.uol.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.uol.exceptions.CodinomeIndisponivelException;
import com.uol.service.CadastroService;
import com.uol.service.domain.Grupo;
import com.uol.service.domain.Jogador;

@Model
@ViewScoped
public class CadastroMB implements Serializable{

	@Inject
	private CadastroService service;
	@Inject
	private HttpSession session;
	
	private Jogador jogador;
	
	//codinomes extraídos do web service
	private List<String> codinomes;		
	
	@PostConstruct
	public void init() {
		
		jogador = (Jogador) session.getAttribute("jogador");
		
		if(jogador != null && jogador.getId() != null)
			//busca no WS os valores de codinomes
			selecionarGrupo();				
		else
			jogador = new Jogador();			
	}	
	
	public String cadastrar() {

		try {			
			jogador = service.cadastrarJogador(jogador);			
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Jogador salvo com sucesso!", ""));
		
			jogador = new Jogador();
		} 
		catch (CodinomeIndisponivelException e) {
			FacesContext.getCurrentInstance()
 						.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
 														e.getMessage(), e.getMessage()));
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
												e.getMessage(), e.getMessage()));
		}
						
		return null;
	}
	
	public String listarJogadores() {
		
		return "lista_jogadores.xhtml";
	}
	
	public void selecionarGrupo() {
		//busca os codinomes no web service
		codinomes = service.getCodinomes(jogador.getGrupo());					
	}
	
	public String listar() {
		return null;
	}

	public Jogador getJogador() {
		return jogador;
	}	

	public Grupo[] getGrupos() {
		
		return Grupo.values();	
	}	

	public List<String> getCodinomes() {
		return codinomes;
	}	
}
