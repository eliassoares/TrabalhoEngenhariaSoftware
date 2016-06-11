package principal;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class AEntradaSaida {
	// Dados gerais
	private static String PRESIDENTE = "presidente";
	private static String SENADOR = "senador";
	
	/**
	 * Sequência de passos que cadastra um candidato
	 * 
	 * @param urna
	 */
	public static void inputCadastrarCandidato(Urna urna) {

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
	public static void inputCadastrarEleitor(Urna urna) {

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
							if(i > 5) AInterfaceUrna.mostraAviso("Você tem algum problema???", "Peça ajuda");
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
						int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que anulará seu voto para presidente?",
								"Confirme seu voto");
						// Confirmou o voto nulo:
						if (escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, -2, PRESIDENTE);
							break;
						}
					}
				}
				// Vai votar em branco:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que irá votar em branco?", "Confirme seu voto");
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
						int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que anulará seu voto para o primeiro senador?", 
								"Confirme seu voto");
						if (escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, -2, SENADOR);
							break;
						}
					}
				}
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {

					int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará em branco?", "Confirme seu voto");					
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
						int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que anulará seu voto para o segundo senador?", 
								"Confirme seu voto");
						if (escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, -2, SENADOR);
							break;
						}
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
					int escolha = AInterfaceUrna.getConfirmacao("Tem certeza que votará em branco?", "Confirme seu voto");
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
			AInterfaceUrna.mostraAviso("Terminando a Eleição", "'Teria sido melhor ter ido ver o filme do Pelé");
			urna.finalizaEleicao();
			return true;
		}
		return false;
	}
}
