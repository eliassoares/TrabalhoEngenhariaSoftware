package principal;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class Urna {

	private static Integer MATRICULA_PADRAO = 0;
	private static Integer SENHA_PADRAO = 0;
	private static String PRESIDENTE = "presidente";
	private static String SENADOR = "senador";

	private Integer numeroDeSerie;
	private List<Eleitor> listaDeEleitores = new LinkedList<>();
	private List<Candidato> listaDePresidentes = new LinkedList<>();
	private List<Candidato> listaDeSenadores = new LinkedList<>();
	private Funcionario funcionario;
	private List<Voto> votos;
	private Integer totalVotos;
	private List<Integer> jaVotaram;

	public Urna(Integer numeroDeSerie, String nomeFuncionario,
			Integer tituloDeEleitor, Integer matricula, Integer senha) {

		// Para iniciar a urna, o funcionário deve ser válido
		while (!this.isFuncionarioValido(matricula, senha)) {

			JOptionPane.showMessageDialog(null,
					"A senha ou a matrícula foi digitada incorretamente!");

			matricula = Integer.valueOf(JOptionPane
					.showInputDialog("Digite a matrícula novamente!"));

			senha = Integer.valueOf(JOptionPane
					.showInputDialog("Digite a senha novamente!"));
		}

		JOptionPane.showMessageDialog(null, "Funcionário logado com sucesso!");

		this.funcionario = new Funcionario(nomeFuncionario, tituloDeEleitor,
				matricula, senha);
		this.numeroDeSerie = numeroDeSerie;
		this.listaDeEleitores
				.add(new Eleitor(nomeFuncionario, tituloDeEleitor));
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

	/**
	 * Cadastra um novo eleitor na urna
	 * 
	 * @param nome
	 * @param tituloDeEleitor
	 * @return Retorna se o eleitor já foi cadastrado ou se é um novo eleitor
	 */
	public boolean cadastrarEleitor(String nome, Integer tituloDeEleitor) {

		// Se o eleitor já foi adicionado previamente
		if (this.isEleitorValido(tituloDeEleitor)) {
			return false;
		}
		this.listaDeEleitores.add(new Eleitor(nome, tituloDeEleitor));
		return true;
	}

	/**
	 * Cadastra um novo candidato na urna.
	 * 
	 * @param nome
	 * @param tituloDeEleitor
	 * @param numeroDeVotacao
	 * @param partido
	 * @param tipoDeCandidato
	 * @param foto
	 * @return
	 */
	public boolean cadastraCandidatos(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato) {

		// -1 é o numero do voto nulo:
		if (numeroDeVotacao.intValue() < 0) {
			return false;
		}

		// Verifica se há um candidato, seja ele presidente ou senador, com o
		// mesmo número de votacao, se sim, recusa a inserção
		for (Candidato c : this.listaDeSenadores) {
			if (c.getNumeroDeVotacao().equals(numeroDeVotacao))
				return false;
		}
		for (Candidato c : this.listaDePresidentes) {
			if (c.getNumeroDeVotacao().equals(numeroDeVotacao))
				return false;
		}

		// Antes de inserir, verifica se ele já foi inserido alguma vez:
		if ((tipoDeCandidato.toLowerCase().equals(PRESIDENTE))
				&& (!this.isPresidenteValido(numeroDeVotacao))) {

			this.listaDePresidentes.add(new Candidato(nome, tituloDeEleitor,
					numeroDeVotacao, partido, tipoDeCandidato));
			this.cadastrarEleitor(nome, tituloDeEleitor);

		} else if ((tipoDeCandidato.toLowerCase().equals(SENADOR))
				&& (!this.isSenadorValido(numeroDeVotacao))) {

			this.listaDeSenadores.add(new Candidato(nome, tituloDeEleitor,
					numeroDeVotacao, partido, tipoDeCandidato));
			this.cadastrarEleitor(nome, tituloDeEleitor);

		} else {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se o funcionário é um funcionário válido
	 * 
	 * @param matricula
	 * @param senha
	 * @return True se é válido, false se náo é válido
	 */
	public boolean isFuncionarioValido(Integer matricula, Integer senha) {

		return (matricula.intValue() == MATRICULA_PADRAO.intValue())
				&& (senha.intValue() == SENHA_PADRAO.intValue());
	}

	/**
	 * Verifica se o presidente é um presidente válido
	 * 
	 * @param numero
	 * @return True se é válido, false se náo é válido
	 */
	public boolean isPresidenteValido(Integer numero) {

		for (Iterator<Candidato> iterator = listaDePresidentes.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			if (cand.getNumeroDeVotacao().intValue() == numero.intValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se o senador é um senador válido
	 * 
	 * @param numero
	 * @return True se é válido, false se náo é válido
	 */
	public boolean isSenadorValido(Integer numero) {

		for (Iterator<Candidato> iterator = listaDeSenadores.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			if (cand.getNumeroDeVotacao().intValue() == numero.intValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se o eleitor é um eleitor válido
	 * 
	 * @param tituloEleitor
	 * @return True se é válido, false se náo é válido
	 */
	public boolean isEleitorValido(Integer tituloEleitor) {

		// Verifica se o eleitor já votou, se sim, ele não possui permissão para
		// votar novamente
		if (this.jaVotaram.contains(tituloEleitor)) {
			return false;
		}

		for (Iterator<Eleitor> iterator = this.listaDeEleitores.iterator(); iterator
				.hasNext();) {

			Eleitor eleitor = (Eleitor) iterator.next();

			if (eleitor.getTituloDeEleitor().intValue() == tituloEleitor
					.intValue()) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Inicia uma nova eleição
	 */
	public void iniciarEleicao() {
		this.votos = new LinkedList<Voto>();
		this.totalVotos = 0;
	}

	/**
	 * Finaliza uma eleição em andamento
	 */
	public void finalizaEleicao() {

	}

	/**
	 * Obtém o nome de um eleitor, dado seu título de eleição
	 * 
	 * @param titulo
	 * @return Se o eleitor é válido, retorna seu nome, se não, retorna null
	 */
	public String getEleitorNome(Integer titulo) {

		for (Iterator<Eleitor> iterator = this.listaDeEleitores.iterator(); iterator
				.hasNext();) {

			Eleitor eleitor = (Eleitor) iterator.next();

			if (eleitor.getTituloDeEleitor().intValue() == titulo.intValue()) {
				return eleitor.getNome();
			}
		}
		return null;
	}

	/**
	 * Obtém as informações dos presidentes que estão registrados na urna
	 * 
	 * @return informações obtidas
	 */
	public ArrayList<String> getPresidentesInformacoes() {

		ArrayList<String> informacoes = new ArrayList<String>();

		for (Iterator<Candidato> iterator = this.listaDePresidentes.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			String string = "Nome: " + (String) cand.getNome() + " Número: "
					+ String.valueOf(cand.getNumeroDeVotacao()) + " Partido: "
					+ cand.getPartido();

			informacoes.add(string);
		}

		return informacoes;
	}

	/**
	 * Obtém as informações dos senadores que estão registrados na urna
	 * 
	 * @return informações obtidas
	 */
	public ArrayList<String> getSenadoresInformacoes() {

		ArrayList<String> informacoes = new ArrayList<String>();

		for (Iterator<Candidato> iterator = this.listaDeSenadores.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			String string = "Nome: " + (String) cand.getNome() + " Número: "
					+ String.valueOf(cand.getNumeroDeVotacao()) + " Partido: "
					+ cand.getPartido();

			informacoes.add(string);
		}

		return informacoes;
	}

	/**
	 * Registra um novo voto
	 * 
	 * @param tituloEleitor
	 * @param numeroCandidato
	 * @param tipoDeCandidato
	 *            pode ser presidente ou senador
	 * @return True, se o voto foi registrado, e false se o eleitor já voltou
	 */
	public boolean computaVoto(Integer tituloEleitor, Integer numeroCandidato,
			String tipoDeCandidato) {

		// Se o eleitor já voltou, não registra seu voto
		if (this.jaVotou(tituloEleitor)) {
			return false;
		}

		this.totalVotos++;

		Voto novo = new Voto(tituloEleitor, numeroCandidato, tipoDeCandidato);

		this.votos.add(novo);

		if (tipoDeCandidato.toLowerCase().equals(SENADOR.toLowerCase())) {
			this.jaVotaram.add(tituloEleitor);
		}

		return true;
	}

	/**
	 * Verifica se um eleitor já votou
	 * 
	 * @param titulo
	 * @return True, se o eleitor já votou, se não voltou, retorna false
	 */
	public boolean jaVotou(Integer titulo) {

		return this.jaVotaram.contains(titulo);
	}

	/**
	 * Retorna o número de votos que um dado candidato recebeu
	 * 
	 * @param c
	 *            candidato que vai ser analisado
	 * @return número de votos
	 */
	public Integer getNumeroVotos(Candidato c) {

		Integer numeroVotos = 0;

		for (Voto v : this.votos) {
			if (v.getNumeroCandidato().equals(c.getNumeroDeVotacao()))
				numeroVotos++;
		}

		return numeroVotos;
	}

	/**
	 * 
	 * @return Retorna o número de votos nulos da eleição
	 */
	public Integer getNumeroVotosNulos() {

		Integer numeroVotos = 0;

		for (Voto v : this.votos) {
			if (v.getNumeroCandidato().equals(-1))
				numeroVotos++;
		}

		return numeroVotos;
	}

	/**
	 * Obtém os resultados da eleição para presidente
	 * 
	 * @return mensagem contendo os resultados da eleiçao para presidente
	 */
	public String getPresidentesResultados() {

		Map<Candidato, Integer> resultados = new HashMap<Candidato, Integer>();

		for (Iterator<Candidato> iterator = this.listaDePresidentes.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			resultados.put(cand, this.getNumeroVotos(cand));

		}

		String s = "";

		for (Candidato cand : resultados.keySet()) {

			s += "Nome: " + cand.getNome() + " Número: "
					+ cand.getNumeroDeVotacao() + " Votos: "
					+ String.valueOf(resultados.get(cand)) + "\n";
		}

		return s;
	}

	/**
	 * Obtém os resultados da eleição para senador
	 * 
	 * @return mensagem contendo os resultados da eleiçao para senador
	 */
	public String getSenadoresResultados() {

		Map<Candidato, Integer> resultados = new HashMap<Candidato, Integer>();

		for (Iterator<Candidato> iterator = this.listaDeSenadores.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			resultados.put(cand, this.getNumeroVotos(cand));
		}

		String s = "";
		for (Candidato cand : resultados.keySet()) {

			s += "Nome: " + cand.getNome() + " Número: "
					+ cand.getNumeroDeVotacao() + " Votos: "
					+ String.valueOf(resultados.get(cand)) + "\n";
		}

		return s;
	}

	/**
	 * Obtém a mensagem contendo os vencedores da eleição
	 * 
	 * @return mensagem contendo os vencedores
	 */
	public String getVencedores() {

		Map<Candidato, Integer> resultadosPresidente = new HashMap<Candidato, Integer>(), resultadosSenador = new HashMap<Candidato, Integer>();
		Candidato presidenteVencedor = null, senadorVencedor = null;
		Integer presidenteVencedorVotos = null, senadorVencedorVotos = null;

		// Obtem os resultados para presidentes
		for (Iterator<Candidato> iterator = this.listaDePresidentes.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			resultadosPresidente.put(cand, this.getNumeroVotos(cand));

		}

		// Obtem o candidato com mais votos
		for (Map.Entry<Candidato, Integer> Entry : resultadosPresidente
				.entrySet()) {

			Candidato candidato = Entry.getKey();
			Integer numVotos = Entry.getValue();

			if (presidenteVencedor == null) {
				presidenteVencedor = candidato;
				presidenteVencedorVotos = numVotos;
			}
			// TODO verificar empate
			else {
				if (presidenteVencedorVotos < numVotos) {
					presidenteVencedor = candidato;
					presidenteVencedorVotos = numVotos;
				}
			}

		}

		// Obtem os resultados para presidentes
		for (Iterator<Candidato> iterator = this.listaDeSenadores.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			resultadosSenador.put(cand, this.getNumeroVotos(cand));

		}

		// Obtem o candidato com mais votos
		for (Map.Entry<Candidato, Integer> Entry : resultadosSenador.entrySet()) {

			Candidato candidato = Entry.getKey();
			Integer numVotos = Entry.getValue();

			if (senadorVencedor == null) {
				senadorVencedor = candidato;
				senadorVencedorVotos = numVotos;
			}
			// TODO verificar empate
			else {
				if (senadorVencedorVotos < numVotos) {
					senadorVencedor = candidato;
					senadorVencedorVotos = numVotos;
				}
			}

		}

		return "Presidente vencedor: " + presidenteVencedor.getNome()
				+ " Partido: " + presidenteVencedor.getPartido() + " Votos: "
				+ presidenteVencedorVotos + '\n' + "Senador vencedor: "
				+ senadorVencedor.getNome() + " Partido: "
				+ senadorVencedor.getPartido() + " Votos: "
				+ senadorVencedorVotos;
	}

	public String getEstatisticasGerais() {

		return this.getNumeroVotosNulos().toString();

	}
}
