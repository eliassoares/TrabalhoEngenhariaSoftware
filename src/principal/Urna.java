package principal;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Urna {

	private Integer numeroDeSerie;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();
	// É importante separar os tipos de candidatos para saber se quando iniciar a eleição, se há no mínimo um de cada.
	private List<Candidato> listaDePresidentes = new LinkedList<>();
	private List<Candidato> listaDeSenadores = new LinkedList<>();
	private Funcionario funcionario;
	private List<Voto> votos;
	private Integer totalVotos;
	private List<Integer> jaVotaram;
	
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
		this.jaVotaram = new LinkedList<Integer>();
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
	
	public boolean cadrastraEleitores(String nome, Integer tituloDeEleitor) {
		//Cara ja foi add:
		if(this.isEleitorValido(tituloDeEleitor)) {
			return false;
		}
		this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
		return true;
	}
	
	public boolean cadastraCandidatos(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato,
			BufferedImage foto) {

		//-1 e o numero do voto nulo:
		if(numeroDeVotacao.intValue() < 0) {
			return false;
		}
		
		//Antes de inserir, verifico se ele já foi inserido alguma vez:
		if((tipoDeCandidato.toLowerCase().equals("presidente")) && (!this.isPresidenteValido(numeroDeVotacao))) {
			this.listaDePresidentes.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
			this.cadrastraEleitores(nome, tituloDeEleitor);
		} else if((tipoDeCandidato.toLowerCase().equals("senador")) && (!this.isSenadorValido(numeroDeVotacao))) {
			this.listaDeSenadores.add(new Candidato(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, foto));
			this.cadrastraEleitores(nome, tituloDeEleitor);
		}
		else {
			return false;
		}
		return true;
	}
	
	public boolean isFuncionarioValido(Integer matricula, Integer senha) {
		Integer senhaPadrao = 1234567;
		Integer matriculaPadrao = 2011049053;
		return (matricula.intValue() == matriculaPadrao.intValue()) && (senha.intValue() == senhaPadrao.intValue());
	}
	
	public boolean isPresidenteValido(Integer numero) {
		for (Iterator<Candidato> iterator = listaDePresidentes.iterator(); iterator.hasNext();) {
			Candidato cand = (Candidato) iterator.next();
			if(cand.getNumeroDeVotacao().intValue() == numero.intValue()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSenadorValido(Integer numero) {
		for (Iterator<Candidato> iterator = listaDeSenadores.iterator(); iterator.hasNext();) {
			Candidato cand = (Candidato) iterator.next();
			if(cand.getNumeroDeVotacao().intValue() == numero.intValue()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEleitorValido(Integer tituloEleitor) {
		// Para um cidadão honesto não votar duas vezes:
		if(this.jaVotaram.contains(tituloEleitor)) {
			return false;
		}
		
		for (Iterator iterator = this.listaDeEleitores.iterator(); iterator.hasNext();) {
			Eleitor eleitor = (Eleitor) iterator.next();
			if(eleitor.getTituloDeEleitor().intValue() == tituloEleitor.intValue()) {
				return true;
			}
			
		}
		return false;
	}

	public void iniciaEleicao() {
		this.votos = new LinkedList<Voto>();
		this.totalVotos = 0;
	}
	
	public void finalizaEleicao() {
		
	}
	
	
	public String getEleitorName(Integer titulo) {
		for (Iterator iterator = this.listaDeEleitores.iterator(); iterator.hasNext();) {
			Eleitor eleitor = (Eleitor) iterator.next();
			if(eleitor.getTituloDeEleitor().intValue() == titulo.intValue()) {
				return eleitor.getNome();
			}
		}
		return null;
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
	
	public boolean computaVoto(Integer tituloEleitor, Integer numeroCandidato) {
		//O escomungado já votou:
		if(this.jaVotou(tituloEleitor)) {
			return false;
		}
		this.totalVotos++;
		Voto novo = new Voto(tituloEleitor, numeroCandidato);
		this.votos.add(novo);
		this.jaVotaram.add(tituloEleitor);
		return true;
	}
	
	public boolean jaVotou(Integer titulo) {
		return this.jaVotaram.contains(titulo);
	}
	
	public String getPresidentesResultados() {
		Map<Candidato, Integer> resultados = new HashMap<Candidato, Integer>();
		
		for (Iterator<Candidato> iterator = this.listaDePresidentes.iterator(); iterator.hasNext();) {
			Candidato cand = (Candidato) iterator.next();
			if(!resultados.containsKey(cand)) {
				System.out.println(resultados.get(cand));
				resultados.put(cand, 1);
				
			} else {
				resultados.put(cand, resultados.get(cand) + 1);
				System.out.println(resultados.containsKey(cand));
			}
		}
		
		String s = "";
		for(Candidato cand : resultados.keySet()){
			s += "Nome: " + cand.getNome() + " Número: " + cand.getNumeroDeVotacao() + " Votos: " + String.valueOf(resultados.get(cand)) + "\n";
        }

		return s;
	}
	
	public String getSenadoresResultados() {
		Map<Candidato, Integer> resultados = new HashMap<Candidato, Integer>();
		
		for (Iterator<Candidato> iterator = this.listaDeSenadores.iterator(); iterator.hasNext();) {
			Candidato cand = (Candidato) iterator.next();
			if(resultados.containsKey(cand)) {
				resultados.put(cand, resultados.get(cand) + 1);
			} else {
				resultados.put(cand, 0);
			}
		}
		
		String s = "";
		for(Candidato cand : resultados.keySet()){
			s += "Nome: " + cand.getNome() + " Número: " + cand.getNumeroDeVotacao() + " Votos: " + String.valueOf(resultados.get(cand)) + "\n";
        }

		return s;
	}
	
	public String getVencedores() {
		return null;
	}
}
