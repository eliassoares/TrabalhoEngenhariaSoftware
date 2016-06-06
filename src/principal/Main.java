package principal;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	public static void vota(Urna urna) {
		String opcoes3[] = {"Tentar novamente", "Sair"};
		Integer tituloEleitor = Integer.valueOf(JOptionPane.showInputDialog(null, "Insira o título de eleitor:"));
		boolean isTituloValido = urna.verificaEleitor(tituloEleitor);
		
		//O gênio errou o título errado:
		while (!isTituloValido) {
			String escolhaOpcao2 = (String) JOptionPane.showInputDialog( null, "Título de eleitor não existe em nosso Banco de dados. O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes3, opcoes3[0]);
			//Tenta inserir novamente:
			if(escolhaOpcao2.equalsIgnoreCase(opcoes3[0])) {
				tituloEleitor = Integer.valueOf(JOptionPane.showInputDialog(null, "Insira o título de eleitor:"));
				isTituloValido = urna.verificaEleitor(tituloEleitor);
			} else if(escolhaOpcao2.equalsIgnoreCase(opcoes3[1])) {
				JOptionPane.showMessageDialog(null, "Próximo Eleitor!");
				break;
			}
			
		}

		if(isTituloValido) {
			ArrayList<String> infoPres = urna.getPresidentesInformacoes();
			ArrayList<String> infoSen = urna.getSenadoresInformacoes();
			String name = urna.getEleitorName(tituloEleitor);
			JOptionPane.showMessageDialog(null, name + " seja um eleitor consciente, escolha bem o demônio que irá eleger!");
			
		}
		
	}
	
	public static boolean tentaFinalizarEleicao(Urna urna) {
		String opcoes3[] = {"Tentar novamente", "Sair"};
		Integer matriculaTeste = Integer.valueOf(JOptionPane.showInputDialog(null, "Funcionário, insira sua matrícula:"));
		Integer senhaTeste = Integer.valueOf(JOptionPane.showInputDialog(null, "Funcionário, insira sua senha:"));
		boolean login = urna.isFuncionarioValido(matriculaTeste, senhaTeste);
		while(!login) {
			
			String escolhaOpcao3 = (String) JOptionPane.showInputDialog( null, "Você inseriu informações erradas. O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes3, opcoes3[0]);
			
			if(escolhaOpcao3.equalsIgnoreCase(opcoes3[0])) {
				matriculaTeste = Integer.valueOf(JOptionPane.showInputDialog(null, "Funcionário, insira sua matrícula:"));
				senhaTeste = Integer.valueOf(JOptionPane.showInputDialog(null, "Funcionário, insira sua senha:"));
				login = urna.isFuncionarioValido(matriculaTeste, senhaTeste);
			}else if (escolhaOpcao3.equalsIgnoreCase(opcoes3[1])) {
				System.out.println(888);
				break;
			}
		}
		
		if(login) {
			JOptionPane.showMessageDialog(null, "Teminando a Eleição!");
			urna.finalizaEleicao();
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Integer senha = 1234567;
		Integer matricula = 2011049053;
		String nomeFuncionario = "Alan Turing";
		Integer titulofuncionario = 2011049053;
		
		Integer numeroDeSerie = 666;
		Urna urna = new Urna(numeroDeSerie, nomeFuncionario, titulofuncionario, matricula,senha);
		
		String opcoes[] = {"Cadastrar Eleitor", "Cadastrar Candidato", "Iniciar Eleição", "Sair"};
		
		while(true) {
			String escolhaOpcao = (String) JOptionPane.showInputDialog( null, "O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes, opcoes[0]);
			// Cadastro de eleitores:
			if(escolhaOpcao.equalsIgnoreCase(opcoes[0])) {
				String nome = JOptionPane.showInputDialog(null,"Insira o nome do eleitor:");
				Integer tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o titulo do eleitor:"));
				urna.cadrastraEleitores(nome, tituloDeEleitor);
			} 
			// Cadastro de candidatos:
			else if(escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
				String nome = JOptionPane.showInputDialog(null,"Insira o nome do candidato:");
				Integer tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o titulo do candidato:"));
				Integer numeroDeVotacao = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o numero do candidato:"));
				String partido = JOptionPane.showInputDialog(null,"Insira o partido do candidato:");
				String tipoDeCandidato = JOptionPane.showInputDialog(null,"Insira o tipo do candidato: ladrão ou assassino");
				urna.cadastraCandidatos(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, null);
			} 
			// Inicia a eleição:
			else if(escolhaOpcao.equalsIgnoreCase(opcoes[2])) {
				if(urna.getQuantidadePresidentes() < 0) {
					JOptionPane.showMessageDialog(null, "Insira no mínimo dois presidentes!", "Quantidade insuficiente de Presidentes",
							JOptionPane.WARNING_MESSAGE);
				}
				else if(urna.getQuantidadeSenadores() < 0) {
					JOptionPane.showMessageDialog(null, "Insira no mínimo dois senadores!", "Quantidade insuficiente de senadores",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "A eleição vai começar!", "Sistema configurado com sucesso!",
							JOptionPane.INFORMATION_MESSAGE);
					urna.iniciaEleicao();
					break;
				}
			} 
			else if(escolhaOpcao.equalsIgnoreCase(opcoes[3])){
				JOptionPane.showMessageDialog(null, "Saindo do sistema");
				System.exit(1989);
			}
		}
		
		String opcoes1[] = {"Votar", "Finalizar Eleição"};
				
		while(true) {
			String escolhaOpcao1 = (String) JOptionPane.showInputDialog( null, "O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes1, opcoes1[0]);
			// Votando:
			if(escolhaOpcao1.equalsIgnoreCase(opcoes1[0])) {
				vota(urna);
			}
			//Finalizando a eleição:
			else if(escolhaOpcao1.equalsIgnoreCase(opcoes1[1])) {
				//retorna true se o login deu certo
				if(tentaFinalizarEleicao(urna)){
					break;
				}
			}
		}

	}

}
