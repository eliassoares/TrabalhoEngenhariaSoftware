// Esse código pode conter Easter Eggs :D
package principal;

import java.util.ArrayList;
import java.util.Iterator;
import principal.AInterfaceUrna;
import javax.swing.JOptionPane;

public class Main {

	// Dados gerais
	private static String PRESIDENTE = "presidente";
	private static String SENADOR = "senador";


	// Dados do funcionario
	private static String FUNCIONARIO_NOME = "Alan Turing";
	private static Integer FUNCIONARIO_MATRICULA = 0;
	private static Integer FUNCIONARIO_SENHA = 0;
	private static Integer FUNCIONARIO_TITULO = 123456789;

	public static void main(String[] args) {

		// Cria uma nova urna
		Urna urna = new Urna(FUNCIONARIO_NOME, FUNCIONARIO_TITULO,
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
			
			String escolhaOpcao = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes, 0);

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
					AInterfaceUrna.mostraAviso("Insira no mínimo dois presidentes!", "Quantidade insuficiente de Presidentes");

				} else if (urna.getQuantidadeSenadores() < 2) {
					AInterfaceUrna.mostraAviso("Insira no mínimo dois senadores!", "Quantidade insuficiente de senadores");
					
				} else {
					AInterfaceUrna.mostraAviso("A eleição vai começar!", "Sistema configurado com sucesso!");
					urna.iniciarEleicao();

					break;

				}
			} else if (escolhaOpcao.equalsIgnoreCase(opcoes[3])) {

				inputSairSistema();
			}
		}

		String opcoes1[] = { "Votar", "Finalizar Eleição" };

		while (true) {

			String escolhaOpcao1 = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes1, 0);

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
		AInterfaceUrna.mostraInformacao("Eleição foi finalizada!", "Fim da seção");

		String opcoes2[] = { "Mostrar Resultados de Todos Candidatos",
				"Mostrar Ganhadores", "Mostrar Estatísticas Gerais", "Sair" };

		while (true) {
			String escolhaOpcao1 = AInterfaceUrna.mostraOpcoes("Funcionário, que deseja fazer?", "Escolha algo", opcoes2, 0);
					
			// Mostrar resultados de todos os candidatos
			if (escolhaOpcao1.equalsIgnoreCase(opcoes2[0])) {
				AInterfaceUrna.mostraInformacao(urna.getPresidentesResultados() + urna.getSenadoresResultados(), "Resultados todos candidatos");
			}
			// Mostrar os ganhadores
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes2[1])) {
				// Mostra os resultados dos vencedores
				AInterfaceUrna.mostraInformacao(urna.getVencedores(), "Vencedores");
			}
			// Mostrar as estatísticas gerais
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes2[2])) {
				AInterfaceUrna.mostraInformacao(urna.getEstatisticasGerais(), "Estatísticas Gerais");
			}
			// Sair
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes2[3])) {
				AInterfaceUrna.mostraInformacao("Saindo do sistema.", "Saindo...");
				break;
			}
		}

		// TODO implementar voto nulo
	}

	/**
	 * Mostra mensagem de sair do sistema
	 */
	private static void inputSairSistema() {
		AInterfaceUrna.mostraAviso("Saindo do sistema", "Saindo...");
	}

	/**
	 * Sequência de passos que cadastra um candidato
	 * 
	 * @param urna
	 */
	private static void inputCadastrarCandidato(Urna urna) {

		String nome = AInterfaceUrna.getValor("Insira o nome do candidato:", "Entre com um nome");

		// executa até receber uma string válida
		while (nome.equals("")) {
			nome = AInterfaceUrna.getValor("Insira o nome do candidato:", "Entre com um nome");
		}

		Integer tituloDeEleitor;

		// executa até receber um número válido
		while (true) {
			try {
				tituloDeEleitor = Integer.valueOf(AInterfaceUrna.getValor( "Insira o titulo do candidato:", "Entre com um valor"));
				if (tituloDeEleitor > 0)
					break;
			} catch (Exception e) {

			}
		}

		Integer numeroDeVotacao;

		// executa até receber um número válido
		while (true) {
			try {
				numeroDeVotacao = Integer.valueOf(AInterfaceUrna.getValor("Insira o numero do candidato:", "Entre com um valor"));
				break;
			} catch (Exception e) {

			}
		}

		String partido = AInterfaceUrna.getValor("Insira o partido do candidato:", "Entre com um valor");

		// executa até receber uma string válida
		while (partido.equals("")) {
			partido = AInterfaceUrna.getValor("Insira o partido do candidato:", "Entre com um valor");
		}

		String tipoDeCandidato = AInterfaceUrna.getValor("Insira o tipo do candidato: Presidente ou Senador", "Entre com um valor");

		// executa até o tipo de candidato digitado ser presidente ou senador
		while (!tipoDeCandidato.toLowerCase().equals(PRESIDENTE)
				&& !tipoDeCandidato.toLowerCase().equals(SENADOR)) {
			tipoDeCandidato = AInterfaceUrna.getValor("Insira o tipo do candidato: presidente ou senador", "Entre com um valor");
		}

		boolean resultadoCadastro = urna.cadastraCandidatos(nome,
				tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato);

		if (resultadoCadastro) {
			AInterfaceUrna.mostraInformacao("Candidato cadatrado com sucesso!", "Sucesso no cadastro.");
		} else {
			String mensagem = "Tipo de candidato inserido não existe ou ele já foi cadastrado anteriormente!";
			AInterfaceUrna.mostraErro(mensagem, "Erro no cadastro");
		}
	}

	/**
	 * Sequência de passos que cadastra um eleitor
	 * 
	 * @param urna
	 */
	private static void inputCadastrarEleitor(Urna urna) {

		String nome = AInterfaceUrna.getValor("Insira o nome do eleitor:", "Entre com um valor");

		// executa ate receber uma string válida
		while (nome.equals("")) {
			nome = AInterfaceUrna.getValor("Insira o nome do eleitor:", "Entre com um valor");
		}

		Integer tituloDeEleitor;

		// executa até receber um número válido
		while (true) {
			try {
				tituloDeEleitor = Integer.valueOf(AInterfaceUrna.getValor("Insira o titulo do eleitor:", "Insira um valor"));
				break;
			} catch (Exception e) {

			}
		}
		
		boolean resultadoCadastro = urna
				.cadastrarEleitor(nome, tituloDeEleitor);

		if (resultadoCadastro) {
			AInterfaceUrna.mostraInformacao("Eleitor cadatrado com sucesso!", "Sucesso no cadastro.");
	
		} else {
			AInterfaceUrna.mostraErro("Eleitor já foi cadrastado anteriormente!", "Falha no cadastro.");
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
				tituloEleitor = Integer.valueOf(AInterfaceUrna.getValor("Insira o título de eleitor:", "Coloque um valor"));
				break;
			} catch (Exception e) {

			}
		}

		boolean isTituloValido = urna.isEleitorValido(tituloEleitor);

		while (!isTituloValido) {
			String mensagem = "Título de eleitor não existe em nosso Banco de dados ou o eleitor já votou. O que deseja fazer?";
			String escolhaOpcao2 = AInterfaceUrna.mostraOpcoes(mensagem, "Escolha algo", opcoes3, 0);

			// Tenta inserir novamente:
			if (escolhaOpcao2.equalsIgnoreCase(opcoes3[0])) {

				tituloEleitor = Integer.valueOf(AInterfaceUrna.getValor("Insira o título de eleitor:", "Coloque de vagarinho!"));

				isTituloValido = urna.isEleitorValido(tituloEleitor);

			} else if (escolhaOpcao2.equalsIgnoreCase(opcoes3[1])) {
				AInterfaceUrna.mostraAviso("Próximo Eleitor!", "A fila andou");
				break;
			}

		}

		if (isTituloValido) {

			ArrayList<String> infoPres = urna.getPresidentesInformacoes();
			ArrayList<String> infoSen = urna.getSenadoresInformacoes();
			String name = urna.getEleitorNome(tituloEleitor);
			String mensagem = name + ", seja um eleitor consciente, escolha bem o candidato que irá votar!";
			AInterfaceUrna.mostraAviso(mensagem, "Vote consciente");

			String[] opcoes = { "Votar", "Votar Branco" };

			String s = "";

			for (Iterator<String> iterator = infoPres.iterator(); iterator
					.hasNext();) {
				s += (String) iterator.next() + "\n";
			}

			while (true) {
				AInterfaceUrna.mostraInformacao(s, "Candidatos para presidente");
				String escolhaOpcao = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes, 0);

				// executa até receber uma string válida
				while (escolhaOpcao.equals("")) {
					escolhaOpcao = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes, 0);
				}

				if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {

					Integer numero;
					int i = 0;
					// executa até receber um número válido
					while (true) {
						try {
							if(i > 5) AInterfaceUrna.mostraAviso("Você tem algum problema mental?", "Peça ajuda");
							numero = Integer.valueOf(AInterfaceUrna.getValor("Insira o número do candidato:", "Entre com o número para presidente"));
							break;
						} catch (Exception e) {
							i ++;
						}
					}

					if (urna.isPresidenteValido(numero)) {
						int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará nesse presidente?", "Confirme seu voto");
						// Confirmou o voto:
						if (escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, numero, PRESIDENTE);
							break;
						}
					} 
					// Vai anular voto
					else {
						AInterfaceUrna.mostraAviso("Número digitado não existe para presidente!", "Número não existe");
					}
				}
				// Vai votar em branco:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que cancelará seu voto?", "Confirme seu voto");
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
			
			Integer numeroSenador1 = 0;
			while (true) {
				AInterfaceUrna.mostraInformacao(s, "Candidatos para senador");
				String escolhaOpcao = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes, 0);

				if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {

					Integer numero;

					// executa até receber um número válido
					while (true) {
						try {
							numero = Integer.valueOf(AInterfaceUrna.getValor("Insira o número do candidato:", "Entre com um valor"));
							numeroSenador1 = numero;
							break;
						} catch (Exception e) {

						}
					}

					if (urna.isSenadorValido(numero)) {

						int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará nesse senador?", "Confirme seu voto");
						// Confirmou o voto:
						if (escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, numero, SENADOR);
							break;
						}
					} else {
						AInterfaceUrna.mostraErro("Número digitado não existe para senador!", "Erro número Senador");
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará em branco?", "Confirme seu voto");
					
					// Confirmou o voto:
					if (escolha == JOptionPane.OK_OPTION) {
						urna.computaVoto(tituloEleitor, -1, SENADOR);
						break;
					}
				}
			}
			
			AInterfaceUrna.mostraInformacao("Vote agora no segundo Senador", "Candidatos para senador");
			
			while (true) {
				String nomeJanela = "Candidatos para senador, não vote no mesmo que votou anteriormente.";
				AInterfaceUrna.mostraInformacao(s, nomeJanela);
				String escolhaOpcao = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes, 0);
				
				if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {

					Integer numero;

					// executa até receber um número válido ou diferente do primeiro senador
					while (true) {
						try {
							numero = Integer.valueOf(AInterfaceUrna.getValor("Insira o número do candidato:", "Entre com um valor"));
							if(numero.intValue() == numeroSenador1.intValue()) {
								mensagem = "Você já votou nesse candidato, por favor, escolha outro!";
								AInterfaceUrna.mostraErro(mensagem, "Senador Inválido");
							}
							else{
								break;
							}
						} catch (Exception e) {

						}
					}

					if (urna.isSenadorValido(numero)) {

						int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará nesse senador?", "Confirme seu voto");

						// Confirmou o voto:
						if (escolha == JOptionPane.OK_OPTION) {

							urna.computaVoto(tituloEleitor, numero, SENADOR);
							break;
						}
					} else {
						AInterfaceUrna.mostraErro("Número digitado não existe para senador!", "Senador Inválido");
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará em branco?", "Confirme seu voto");
					
					// Confirmou o voto:
					if (escolha == JOptionPane.OK_OPTION) {

						urna.computaVoto(tituloEleitor, -1, SENADOR);
						break;
					}
				}
			}
			AInterfaceUrna.mostraInformacao("Seu voto foi salvo!", "Voto cadastrado com sucesso");
		}

	}

	public static boolean tentaFinalizarEleicao(Urna urna) {

		String opcoes3[] = { "Tentar novamente", "Sair" };

		Integer matriculaTeste;

		// executa até receber um número válido
		while (true) {
			try {
				matriculaTeste = Integer.valueOf(AInterfaceUrna.getValor("Funcionário, insira sua matrícula:", "Entre com um valor"));
				break;
			} catch (Exception e) {

			}
		}

		Integer senhaTeste;

		// executa até receber um número válido
		while (true) {
			try {
				senhaTeste = Integer.valueOf(AInterfaceUrna.getValor("Funcionário, insira sua senha:", "Entre com um valor"));
				break;
			} catch (Exception e) {

			}
		}

		boolean login = urna.isFuncionarioValido(matriculaTeste, senhaTeste);

		while (!login) {
			String mensagem = "Você inseriu informações erradas. O que deseja fazer?";
			
			String escolhaOpcao3 = AInterfaceUrna.mostraOpcoes(mensagem, "Escolha algo", opcoes3, 0);

			if (escolhaOpcao3.equalsIgnoreCase(opcoes3[0])) {

				matriculaTeste = Integer.valueOf(AInterfaceUrna.getValor("Funcionário, insira sua matrícula:", "Entre com um valor"));
				senhaTeste = Integer.valueOf(AInterfaceUrna.getValor("Funcionário, insira sua senha:", "Entre com um valor"));
				login = urna.isFuncionarioValido(matriculaTeste, senhaTeste);

			} else if (escolhaOpcao3.equalsIgnoreCase(opcoes3[1])) {

				break;
			}
		}

		if (login) {
			AInterfaceUrna.mostraAviso("Terminando a Eleição", "'Teria sido melhor ter ido ver o filme do Pelé'");
			urna.finalizaEleicao();
			return true;
		}
		return false;
	}

}
