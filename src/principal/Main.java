// Esse código pode conter Easter Eggs :D
package principal;

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

		String opcoes[] = { "Cadastrar Eleitor", "Cadastrar Candidato",
				"Iniciar Eleição", "Sair" };

		while (true) {
			
			String escolhaOpcao = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes, 0);

			// Cadastro de eleitores:
			if (escolhaOpcao.equalsIgnoreCase(opcoes[0])) {
				AEntradaSaida.inputCadastrarEleitor(urna);
			}
			// Cadastro de candidatos:
			else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
				AEntradaSaida.inputCadastrarCandidato(urna);
			}
			// Inicia a eleição:
			else if (escolhaOpcao.equalsIgnoreCase(opcoes[2])) {
				if (urna.getQuantidadePresidentes() < 2) {
					AInterfaceUrna.mostraAviso("Insira no mínimo dois presidentes!", "Quantidade insuficiente de Presidentes");
				} else if (urna.getQuantidadeSenadores() < 3) {
					AInterfaceUrna.mostraAviso("Insira no mínimo dois senadores!", "Quantidade insuficiente de senadores");
				} else {
					AInterfaceUrna.mostraAviso("A eleição vai começar!", "Sistema configurado com sucesso!");
					urna.iniciarEleicao();
					break;
				}
			} else if (escolhaOpcao.equalsIgnoreCase(opcoes[3])) {
				AInterfaceUrna.mostraAviso("Saindo do sistema", "Saindo...");
				return;
			}
		}

		String opcoes1[] = { "Votar", "Finalizar Eleição" };

		while (true) {

			String escolhaOpcao1 = AInterfaceUrna.mostraOpcoes("O que deseja fazer?", "Escolha algo", opcoes1, 0);

			// Realiza votação
			if (escolhaOpcao1.equalsIgnoreCase(opcoes1[0])) {
				AEntradaSaida.inputVotar(urna);
			}
			// Finaliza a eleição:
			else if (escolhaOpcao1.equalsIgnoreCase(opcoes1[1])) {

				// retorna true se o login deu certo
				if (AEntradaSaida.tentaFinalizarEleicao(urna)) {
					break;
				}
			}
		}
		
		AInterfaceUrna.mostraInformacao("Eleição foi finalizada!", "'Teria sido melhor ir ver o filme do pelé'");
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
	}

}
// O código pode conter Easter Eggs