package principal;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Main {

	// Dados gerais
	private static String PRESIDENTE = "presidente";
	private static String SENADOR = "senador";

	// Dados da urna
	private static Integer NUM_SERIE = 777;

	// Dados do funcionario
	private static String FUNCIONARIO_NOME = "Alan Turing";
	private static Integer FUNCIONARIO_MATRICULA = 0;
	private static Integer FUNCIONARIO_SENHA = 0;
	private static Integer FUNCIONARIO_TITULO = 123456789;

	public static void main(String[] args) {

		// Cria uma nova urna
		Urna urna = new Urna(NUM_SERIE, FUNCIONARIO_NOME, FUNCIONARIO_TITULO,
				FUNCIONARIO_MATRICULA, FUNCIONARIO_SENHA);

		// TODO REMOVER
		urna.cadastraCandidatos("Dilma", 1313, 13, "PT", PRESIDENTE);
		urna.cadastraCandidatos("Aecio", 4545, 45, "PSDB", PRESIDENTE);
		urna.cadastraCandidatos("Delcidio", 131313, 130, "PT", SENADOR);
		urna.cadastraCandidatos("Juca", 454545, 450, "PSDB", SENADOR);
		urna.cadastrarEleitor("Eleitor 1", 1);
		urna.cadastrarEleitor("Eleitor 2", 2);
		urna.cadastrarEleitor("Eleitor 3", 3);
		urna.cadastrarEleitor("Eleitor 4", 4);

		String opcoes[] = { "Cadastrar Eleitor", "Cadastrar Candidato",
				"Iniciar Eleição", "Sair" };

		while (true) {

			String escolhaOpcao = (String) JOptionPane.showInputDialog(null,
					"O que deseja fazer?", "Escolha algo",
					JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			// Cadastro de eleitores:
			if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {

				inputCadastrarEleitor(urna);
			}
			// Cadastro de candidatos:
			else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

				inputCadastrarCandidato(urna);
			}
			// Inicia a eleição:
			else if (escolhaOpcao.equalsIgnoreCase(opcoes[2])) {

				if (urna.getQuantidadePresidentes() < 2) {

					JOptionPane.showMessageDialog(null,
							"Insira no mínimo dois presidentes!",
							"Quantidade insuficiente de Presidentes",
							JOptionPane.WARNING_MESSAGE);

				} else if (urna.getQuantidadeSenadores() < 2) {

					JOptionPane.showMessageDialog(null,
							"Insira no mínimo dois senadores!",
							"Quantidade insuficiente de senadores",
							JOptionPane.WARNING_MESSAGE);

				} else {

					JOptionPane.showMessageDialog(null,
							"A eleição vai começar!",
							"Sistema configurado com sucesso!",
							JOptionPane.INFORMATION_MESSAGE);

					urna.iniciarEleicao();

					break;

				}
			} else if (escolhaOpcao.equalsIgnoreCase(opcoes[3])) {

				inputSairSistema();
			}
		}

		String opcoes1[] = { "Votar", "Finalizar Eleição" };

		while (true) {

			String escolhaOpcao1 = (String) JOptionPane.showInputDialog(null,
					"O que deseja fazer?", "Escolha algo",
					JOptionPane.QUESTION_MESSAGE, null, opcoes1, opcoes1[0]);

			// Realiza votação
			if (escolhaOpcao1.equalsIgnoreCase(opcoes1[0])) {

				inputVotar(urna);
			}
			// Finaliza a eleição:
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes1[1])) {

				// retorna true se o login deu certo
				if (tentaFinalizarEleicao(urna)) {
					break;
				}
			}
		}

		JOptionPane.showMessageDialog(null, "Eleição foi finalizada!");

		String opcoes2[] = { "Mostrar Resultados de Todos Candidatos",
				"Mostrar Ganhadores", "Mostrar Estatísticas Gerais", "Sair" };

		while (true) {
			String escolhaOpcao1 = (String) JOptionPane.showInputDialog(null,
					"Funcionário, que deseja fazer?", "Escolha algo",
					JOptionPane.QUESTION_MESSAGE, null, opcoes2, opcoes2[3]);
			// Mostrar resultados de todos os candidatos
			if (escolhaOpcao1.equalsIgnoreCase(opcoes2[0])) {
				JOptionPane.showMessageDialog(
						null,
						urna.getPresidentesResultados()
								+ urna.getSenadoresResultados());
			}
			// Mostrar os ganhadores
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes2[1])) {
				// Mostra os resultados dos vencedores
				JOptionPane.showMessageDialog(null, urna.getVencedores());
			}
			// Mostrar as estatísticas gerais
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes2[2])) {
				JOptionPane.showMessageDialog(null,
						urna.getEstatisticasGerais());
			}
			// Sair
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes2[3])) {
				JOptionPane.showMessageDialog(null, "Saindo do sistema.");
				break;
			}
		}

		// TODO implementar voto nulo
	}

	/**
	 * Mostra mensagem de sair do sistema
	 */
	private static void inputSairSistema() {
		JOptionPane.showMessageDialog(null, "Saindo do sistema");
		System.exit(1989);
	}

	/**
	 * Sequência de passos que cadastra um candidato
	 * 
	 * @param urna
	 */
	private static void inputCadastrarCandidato(Urna urna) {

		String nome = JOptionPane.showInputDialog(null,
				"Insira o nome do candidato:");

		// executa até receber uma string válida
		while (nome.equals("")) {
			nome = JOptionPane.showInputDialog(null,
					"Insira o nome do candidato:");
		}

		Integer tituloDeEleitor;

		// executa até receber um número válido
		while (true) {
			try {
				tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Insira o titulo do candidato:"));
				if (tituloDeEleitor > 0)
					break;
			} catch (Exception e) {

			}
		}

		Integer numeroDeVotacao;

		// executa até receber um número válido
		while (true) {
			try {
				numeroDeVotacao = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Insira o numero do candidato:"));
				break;
			} catch (Exception e) {

			}
		}

		String partido = JOptionPane.showInputDialog(null,
				"Insira o partido do candidato:");

		// executa até receber uma string válida
		while (partido.equals("")) {
			partido = JOptionPane.showInputDialog(null,
					"Insira o partido do candidato:");
		}

		String tipoDeCandidato = JOptionPane.showInputDialog(null,
				"Insira o tipo do candidato: Presidente ou Senador");

		// executa até o tipo de candidato digitado ser presidente ou senador
		while (!tipoDeCandidato.toLowerCase().equals(PRESIDENTE)
				&& !tipoDeCandidato.toLowerCase().equals(SENADOR)) {
			tipoDeCandidato = JOptionPane.showInputDialog(null,
					"Insira o tipo do candidato: presidente ou senador");
		}

		boolean resultadoCadastro = urna.cadastraCandidatos(nome,
				tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato);

		if (resultadoCadastro) {
			JOptionPane.showMessageDialog(null,
					"Candidato cadatrado com sucesso!", "Sucesso no cadastro.",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Tipo de candidato inserido não existe ou ele já foi cadastrado anteriormente!",
							"Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Sequência de passos que cadastra um eleitor
	 * 
	 * @param urna
	 */
	private static void inputCadastrarEleitor(Urna urna) {

		String nome = JOptionPane.showInputDialog(null,
				"Insira o nome do eleitor:");

		// executa ate receber uma string válida
		while (nome.equals("")) {
			nome = JOptionPane.showInputDialog(null,
					"Insira o nome do eleitor:");
		}

		Integer tituloDeEleitor;

		// executa até receber um número válido
		while (true) {
			try {
				tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Insira o titulo do eleitor:"));
				break;
			} catch (Exception e) {

			}
		}

		boolean resultadoCadastro = urna
				.cadastrarEleitor(nome, tituloDeEleitor);

		if (resultadoCadastro) {
			JOptionPane.showMessageDialog(null,
					"Eleitor cadatrado com sucesso!", "Sucesso no cadastro.",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
					"Eleitor já foi cadrastado anteriormente!",
					"Falha no cadastro.", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Sequência de passos para realizar uma votação
	 * 
	 * @param urna
	 */
	public static void inputVotar(Urna urna) {

		String opcoes3[] = { "Tentar novamente", "Sair" };

		Integer tituloEleitor;

		// executa até receber um número válido
		while (true) {
			try {
				tituloEleitor = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Insira o título de eleitor:"));
				break;
			} catch (Exception e) {

			}
		}

		boolean isTituloValido = urna.isEleitorValido(tituloEleitor);

		while (!isTituloValido) {

			String escolhaOpcao2 = (String) JOptionPane
					.showInputDialog(
							null,
							"Título de eleitor não existe em nosso Banco de dados ou o eleitor já votou. O que deseja fazer?",
							"Escolha algo", JOptionPane.QUESTION_MESSAGE, null,
							opcoes3, opcoes3[0]);

			// Tenta inserir novamente:
			if (escolhaOpcao2.equalsIgnoreCase(opcoes3[0])) {

				tituloEleitor = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Insira o título de eleitor:"));

				isTituloValido = urna.isEleitorValido(tituloEleitor);

			} else if (escolhaOpcao2.equalsIgnoreCase(opcoes3[1])) {

				JOptionPane.showMessageDialog(null, "Próximo Eleitor!");
				break;
			}

		}

		if (isTituloValido) {

			ArrayList<String> infoPres = urna.getPresidentesInformacoes();
			ArrayList<String> infoSen = urna.getSenadoresInformacoes();
			String name = urna.getEleitorNome(tituloEleitor);

			JOptionPane
					.showMessageDialog(
							null,
							name
									+ ", seja um eleitor consciente, escolha bem o candidato que irá votar!");

			String[] opcoes = { "Votar", "Anular Voto" };

			String s = "";

			for (Iterator<String> iterator = infoPres.iterator(); iterator
					.hasNext();) {
				s += (String) iterator.next() + "\n";
			}

			while (true) {

				JOptionPane.showMessageDialog(null, s,
						"Candidatos para presidente",
						JOptionPane.INFORMATION_MESSAGE);

				String escolhaOpcao = (String) JOptionPane.showInputDialog(
						null, "O que deseja fazer?", "Escolha algo",
						JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

				// executa até receber uma string válida
				while (escolhaOpcao.equals("")) {

					escolhaOpcao = (String) JOptionPane.showInputDialog(null,
							"O que deseja fazer?", "Escolha algo",
							JOptionPane.QUESTION_MESSAGE, null, opcoes,
							opcoes[0]);
				}

				if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {

					Integer numero;

					// executa até receber um número válido
					while (true) {
						try {
							numero = Integer.valueOf(JOptionPane
									.showInputDialog(null,
											"Insira o número do candidato:"));
							break;
						} catch (Exception e) {

						}
					}

					if (urna.isPresidenteValido(numero)) {

						int escolha = JOptionPane.showConfirmDialog(null,
								"Tem certeza que votará nesse presidente?",
								"Confirme seu voto", JOptionPane.OK_OPTION);

						// Confirmou o voto:
						if (escolha == JOptionPane.OK_OPTION) {

							urna.computaVoto(tituloEleitor, numero, PRESIDENTE);
							break;
						}
					} else {

						JOptionPane.showMessageDialog(null,
								"Número digitado não existe para presidente!");
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = JOptionPane.showConfirmDialog(null,
							"Tem certeza que cancelará seu voto?",
							"Confirme seu voto", JOptionPane.OK_OPTION);

					// Confirmou o voto:
					if (escolha == JOptionPane.OK_OPTION) {
						urna.computaVoto(tituloEleitor, -1, PRESIDENTE);
						break;
					}

				}
			}

			// Voto para senador:
			s = "";

			for (Iterator<String> iterator = infoSen.iterator(); iterator
					.hasNext();) {

				s += (String) iterator.next() + "\n";
			}

			while (true) {

				JOptionPane.showMessageDialog(null, s,
						"Candidatos para senador",
						JOptionPane.INFORMATION_MESSAGE);

				String escolhaOpcao = (String) JOptionPane.showInputDialog(
						null, "O que deseja fazer?", "Escolha algo",
						JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

				if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {

					Integer numero;

					// executa até receber um número válido
					while (true) {
						try {
							numero = Integer.valueOf(JOptionPane
									.showInputDialog(null,
											"Insira o número do candidato:"));
							break;
						} catch (Exception e) {

						}
					}

					if (urna.isSenadorValido(numero)) {

						int escolha = JOptionPane.showConfirmDialog(null,
								"Tem certeza que votará nesse senador?",
								"Confirme seu voto", JOptionPane.OK_OPTION);

						// Confirmou o voto:
						if (escolha == JOptionPane.OK_OPTION) {

							urna.computaVoto(tituloEleitor, numero, SENADOR);
							break;
						}
					} else {

						JOptionPane.showMessageDialog(null,
								"Número digitado não existe para senador!");
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = JOptionPane.showConfirmDialog(null,
							"Tem certeza que cancelará seu voto?",
							"Confirme seu voto", JOptionPane.OK_OPTION);

					// Confirmou o voto:
					if (escolha == JOptionPane.OK_OPTION) {

						urna.computaVoto(tituloEleitor, -1, SENADOR);
						break;
					}
				}
			}

			JOptionPane.showMessageDialog(null, "Seu voto foi salvo!");
		}

	}

	public static boolean tentaFinalizarEleicao(Urna urna) {

		String opcoes3[] = { "Tentar novamente", "Sair" };

		Integer matriculaTeste;

		// executa até receber um número válido
		while (true) {
			try {
				matriculaTeste = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Funcionário, insira sua matrícula:"));
				break;
			} catch (Exception e) {

			}
		}

		Integer senhaTeste;

		// executa até receber um número válido
		while (true) {
			try {
				senhaTeste = Integer.valueOf(JOptionPane.showInputDialog(null,
						"Funcionário, insira sua senha:"));
				break;
			} catch (Exception e) {

			}
		}

		boolean login = urna.isFuncionarioValido(matriculaTeste, senhaTeste);

		while (!login) {

			String escolhaOpcao3 = (String) JOptionPane.showInputDialog(null,
					"Você inseriu informações erradas. O que deseja fazer?",
					"Escolha algo", JOptionPane.QUESTION_MESSAGE, null,
					opcoes3, opcoes3[0]);

			if (escolhaOpcao3.equalsIgnoreCase(opcoes3[0])) {

				matriculaTeste = Integer.valueOf(JOptionPane.showInputDialog(
						null, "Funcionário, insira sua matrícula:"));
				senhaTeste = Integer.valueOf(JOptionPane.showInputDialog(null,
						"Funcionário, insira sua senha:"));
				login = urna.isFuncionarioValido(matriculaTeste, senhaTeste);

			} else if (escolhaOpcao3.equalsIgnoreCase(opcoes3[1])) {

				break;
			}
		}

		if (login) {
			JOptionPane.showMessageDialog(null, "Teminando a Eleição!");
			urna.finalizaEleicao();
			return true;
		}
		return false;
	}

}
