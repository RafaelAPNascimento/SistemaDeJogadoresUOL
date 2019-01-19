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

import com.uol.service.CadastroService;
import com.uol.service.domain.Jogador;

@Model
@ViewScoped
public class ListaJogadoresMB implements Serializable{

	@Inject
	private CadastroService service;
	@Inject
	private HttpSession session;
	private List<Jogador> jogadores;
	private Jogador jogador;
	
	@PostConstruct
	public void init() {
		jogadores = service.getJogadores();			
	}
	
	public String excluir() {
		
		boolean deletou = service.excluirJogador(jogador);
		
		if(deletou) {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jogador Excluido", ""));
		
			jogadores.remove(jogador);
		}		
		return null;
	}
	
	public String editar() {		
		//adiciona o jogador a ser editado na sessão para o outro managed bean recupera-lo
		session.setAttribute("jogador", jogador);
		return "cadastro_uol.xhtml";
	}
	
	public String novoJogador() {
		//remove da sessão pra que os campos não  apareçam no formulário da página de cadastro
		session.removeAttribute("jogador");
		return "cadastro_uol.xhtml";
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}	
}
