package principal;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class Urna {

	private Integer numeroDeSerie;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();
	// É importante separar os tipos de candidatos para saber se quando iniciar a eleição, se há no mínimo um de cada.
	private List<Candidato> listaDePresidentes = new LinkedList<>();
	private List<Candidato> listaDeSenadores = new LinkedList<>();
	private Funcionario funcionario;
	private Eleicao eleicao;
	
	public Urna(Integer numeroDeSerie, String nomeFuncionario, Integer tituloDeEleitor, Integer matricula, Integer senha) {
		
		while(!this.isFuncionarioValido(matricula, senha)) {
			JOptionPane.showMessageDialog(null, "A senha ou a matrícula foi digitada incorretamente!");
			matricula = Integer.valueOf(JOptionPane.showInputDialog("Digite a matrícula novamente!"));
			senha = Integer.valueOf(JOptionPane.showInputDialog("Digite a senha novamente!"));
		}
		JOptionPane.showMessageDialog(null, "Funcionário logado com sucesso!");
		
		this.funcionario = new Funcionario(nomeFuncionario, tituloDeEleitor, matricula, senha);
		this.numeroDeSerie = numeroDeSerie;
		this.listaDeEleitores.add(new Eleitor(nomeFuncionario, tituloDeEleitor));
	}

	public void setNumeroDeSerie(Integer numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public Integer getQuantidadePresidentes() {
		return this.listaDePresidentes.size();
	}
	
	public Integer getQuantidadeSenadores() {
		return this.listaDeSenadores.size();
	}
	
	public Integer getQuantidadeEleitores() {
		return this.listaDeEleitores.size();
	}
	
	public void cadrastraEleitores(String nome, Integer tituloDeEleitor) {
		this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
		JOptionPane.showMessageDialog(null, "Eleitor cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void cadastraCandidatos(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato,
			BufferedImage foto) {
		
		if(tipoDeCandidato.toLowerCase().equals("presidente")) {
			this.listaDePresidentes.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
			this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
			JOptionPane.showMessageDialog(null, "Candidato cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
		} else if(tipoDeCandidato.toLowerCase().equals("senador")) {
			this.listaDeSenadores.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
			JOptionPane.showMessageDialog(null, "Candidato cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
			this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
		}
		else {
			JOptionPane.showMessageDialog(null, "Tipo de candidato inserido não existe!", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean isFuncionarioValido(Integer matricula, Integer senha) {
		Integer senhaPadrao = 1234567;
		Integer matriculaPadrao = 2011049053;
		return (matricula.intValue() == matriculaPadrao.intValue()) && (senha.intValue() == senhaPadrao.intValue());
	}
	
	public void iniciaEleicao() {
		this.eleicao = new Eleicao(this.listaDePresidentes, this.listaDeSenadores, this.listaDeEleitores);
	}
	
	public void finalizaEleicao() {
		
	}
	
	public boolean verificaEleitor(Integer tituloEleitor) {
		return this.eleicao.hasEleitor(tituloEleitor);
	}
	
	public String getEleitorName(Integer titulo) {
		return this.eleicao.getEleitorName(titulo);
	}
	
	public ArrayList<String> getPresidentesInformacoes(){
		ArrayList<String> informacoes = new ArrayList<String>();
		
		for (Iterator<Candidato> iterator = this.listaDePresidentes.iterator(); iterator.hasNext();) {
			Candidato cand = (Candidato) iterator.next();
			String string = "Nome: " + (String)cand.getNome() + " Número: " + String.valueOf(cand.getNumeroDeVotacao()) + " Partido: " + cand.getPartido();
			informacoes.add(string);
		}
		return informacoes;
	}
	
	public ArrayList<String> getSenadoresInformacoes(){
		ArrayList<String> informacoes = new ArrayList<String>();
		
		for (Iterator<Candidato> iterator = this.listaDeSenadores.iterator(); iterator.hasNext();) {
			Candidato cand = (Candidato) iterator.next();
			String string = "Nome: " + (String)cand.getNome() + " Número: " + String.valueOf(cand.getNumeroDeVotacao()) + " Partido: " + cand.getPartido();
			informacoes.add(string);
		}
		return informacoes;
	}
}
