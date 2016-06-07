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
	// É importante separar os tipos de candidatos para saber se quando iniciar
	// a eleição, se há no mínimo um de cada.
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
			Integer numeroDeVotacao, String partido, String tipoDeCandidato,
			BufferedImage foto) {

		// -1 é o numero do voto nulo:
		if (numeroDeVotacao.intValue() < 0) {
			return false;
		}

		// Antes de inserir, verifica se ele já foi inserido alguma vez:
		if ((tipoDeCandidato.toLowerCase().equals("presidente"))
				&& (!this.isPresidenteValido(numeroDeVotacao))) {

			this.listaDePresidentes.add(new Candidato(nome, tituloDeEleitor,
					numeroDeVotacao, partido, tipoDeCandidato, foto));
			this.cadastrarEleitor(nome, tituloDeEleitor);

		} else if ((tipoDeCandidato.toLowerCase().equals("senador"))
				&& (!this.isSenadorValido(numeroDeVotacao))) {

			this.listaDeSenadores.add(new Candidato(nome, tituloDeEleitor,
					numeroDeVotacao, partido, tipoDeCandidato, foto));
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

		Integer senhaPadrao = 1234567;
		Integer matriculaPadrao = 2011049053;

		return (matricula.intValue() == matriculaPadrao.intValue())
				&& (senha.intValue() == senhaPadrao.intValue());
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
	 * @return True, se o voto foi registrado, e false se o eleitor já voltou
	 */
	public boolean computarVoto(Integer tituloEleitor, Integer numeroCandidato) {

		// Se o eleitor já voltou, não registra seu voto
		if (this.jaVotou(tituloEleitor)) {
			return false;
		}

		this.totalVotos++;

		Voto novo = new Voto(tituloEleitor, numeroCandidato);

		this.votos.add(novo);

		this.jaVotaram.add(tituloEleitor);

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
	 * Obtém os resultados da eleição para presidente
	 * 
	 * @return mensagem contendo os resultados da eleiçao para presidente
	 */
	public String getPresidentesResultados() {

		Map<Candidato, Integer> resultados = new HashMap<Candidato, Integer>();

		for (Iterator<Candidato> iterator = this.listaDePresidentes.iterator(); iterator
				.hasNext();) {

			Candidato cand = (Candidato) iterator.next();

			if (!resultados.containsKey(cand)) {

				System.out.println(resultados.get(cand));
				resultados.put(cand, 1);

			} else {

				resultados.put(cand, resultados.get(cand) + 1);
				System.out.println(resultados.containsKey(cand));
			}
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

			if (resultados.containsKey(cand)) {

				resultados.put(cand, resultados.get(cand) + 1);
			} else {

				resultados.put(cand, 0);
			}
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
		throw new IllegalStateException("IMPLEMENTAR");
		// return null;
	}
}
