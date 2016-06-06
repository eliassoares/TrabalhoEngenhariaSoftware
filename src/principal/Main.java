package principal;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Main {

	public static void vota(Urna urna) {
		String opcoes3[] = {"Tentar novamente", "Sair"};
		Integer tituloEleitor = Integer.valueOf(JOptionPane.showInputDialog(null, "Insira o título de eleitor:"));
		boolean isTituloValido = urna.isEleitorValido(tituloEleitor);
		
		//O gênio errou o título errado:
		while (!isTituloValido) {
			String escolhaOpcao2 = (String) JOptionPane.showInputDialog( null, "Título de eleitor não existe em nosso Banco de dados ou o eleitor já votou. O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes3, opcoes3[0]);
			//Tenta inserir novamente:
			if(escolhaOpcao2.equalsIgnoreCase(opcoes3[0])) {
				tituloEleitor = Integer.valueOf(JOptionPane.showInputDialog(null, "Insira o título de eleitor:"));
				isTituloValido = urna.isEleitorValido(tituloEleitor);
			} else if(escolhaOpcao2.equalsIgnoreCase(opcoes3[1])) {
				JOptionPane.showMessageDialog(null, "Próximo Eleitor!");
				break;
			}
			
		}
		

		if(isTituloValido) {
			ArrayList<String> infoPres = urna.getPresidentesInformacoes();
			ArrayList<String> infoSen = urna.getSenadoresInformacoes();
			String name = urna.getEleitorName(tituloEleitor);
			JOptionPane.showMessageDialog(null, name + " seja um eleitor consciente, escolha bem o demônio que irá votar!");
			
			String[] opcoes = {"Votar", "Anular Voto"};
			
			String s = "";
			for (Iterator<String> iterator = infoPres.iterator(); iterator.hasNext();) {
				s += (String) iterator.next() + "\n";
			}
			
			while(true) {
				JOptionPane.showMessageDialog(null, s, "Candidatos para presidente", JOptionPane.INFORMATION_MESSAGE);
				String escolhaOpcao = (String) JOptionPane.showInputDialog( null, "O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
				        null, opcoes, opcoes[0]);
				// Vai fazer burrada:
				if(escolhaOpcao.equalsIgnoreCase(opcoes[0])) {
					Integer numero = Integer.valueOf(JOptionPane.showInputDialog(null, "Insira o número do candidato:"));
					
					if(urna.isPresidenteValido(numero)) {
						int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que votará nesse ser vivo?", "Confirme seu voto", JOptionPane.OK_OPTION);
						
						//Confirmou o voto:
						if(escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, numero);
							break;
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Número digitado não existe para presidente!");
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
					int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que cancelará seu voto?", "Confirme seu voto", JOptionPane.OK_OPTION);
					
					//Confirmou o voto:
					if(escolha == JOptionPane.OK_OPTION) {
						urna.computaVoto(tituloEleitor, -1);
						break;
					}
					
				}
			}
			
			
			//Voto para senador:
			s = "";
			for (Iterator<String> iterator = infoSen.iterator(); iterator.hasNext();) {
				s += (String) iterator.next() + "\n";
			}
			
			while(true) {
				JOptionPane.showMessageDialog(null, s, "Candidatos para senador", JOptionPane.INFORMATION_MESSAGE);
				String escolhaOpcao = (String) JOptionPane.showInputDialog( null, "O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
				        null, opcoes, opcoes[0]);
				// Vai fazer burrada:
				if(escolhaOpcao.equalsIgnoreCase(opcoes[0])) {
					Integer numero = Integer.valueOf(JOptionPane.showInputDialog(null, "Insira o número do candidato:"));
					
					if(urna.isPresidenteValido(numero)) {
						int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que votará nesse ser vivo?", "Confirme seu voto", JOptionPane.OK_OPTION);
						
						//Confirmou o voto:
						if(escolha == JOptionPane.OK_OPTION) {
							urna.computaVoto(tituloEleitor, numero);
							break;
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Número digitado não existe para senador!");
					}
				}
				// Vai cancelar voto:
				else if (escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
					int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que cancelará seu voto?", "Confirme seu voto", JOptionPane.OK_OPTION);
					
					//Confirmou o voto:
					if(escolha == JOptionPane.OK_OPTION) {
						urna.computaVoto(tituloEleitor, -1);
						break;
					}
				}
			}
			
			JOptionPane.showMessageDialog(null, "Seu voto foi salvo!");
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
		
		//Pra adiantar os testes:
		urna.cadastraCandidatos("Dilma Pão com Salame", 131313, 13, "PT", "Presidente", null);
		urna.cadastraCandidatos("Aecio CheiraPó", 454545, 45, "PSDB", "Presidente", null);
		urna.cadastraCandidatos("Sou um eleitor que não conhece os senadores", 13131, 13, "PT", "senador", null);
		urna.cadastraCandidatos("Que orgulho de mim", 45454, 45, "PSDB", "senador", null);
		urna.cadrastraEleitores("Elias Soares", 8);
		
		String opcoes[] = {"Cadastrar Eleitor", "Cadastrar Candidato", "Iniciar Eleição", "Sair"};
		
		while(true) {
			String escolhaOpcao = (String) JOptionPane.showInputDialog( null, "O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes, opcoes[0]);
			// Cadastro de eleitores:
			if(escolhaOpcao.equalsIgnoreCase(opcoes[0])) {
				String nome = JOptionPane.showInputDialog(null,"Insira o nome do eleitor:");
				Integer tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o titulo do eleitor:"));
				boolean resultadoCadastro = urna.cadrastraEleitores(nome, tituloDeEleitor);
				if(resultadoCadastro) {
					JOptionPane.showMessageDialog(null, "Eleitor cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Eleitor já foi cadrastado anteriormente!", "Falha no cadastro.", JOptionPane.ERROR_MESSAGE);
				}
				
			} 
			// Cadastro de candidatos:
			else if(escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
				String nome = JOptionPane.showInputDialog(null,"Insira o nome do candidato:");
				Integer tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o titulo do candidato:"));
				Integer numeroDeVotacao = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o numero do candidato:"));
				String partido = JOptionPane.showInputDialog(null,"Insira o partido do candidato:");
				String tipoDeCandidato = JOptionPane.showInputDialog(null,"Insira o tipo do candidato: ladrão ou assassino");
				boolean resultadoCadastro = urna.cadastraCandidatos(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, null);
				
				if(resultadoCadastro) {
					JOptionPane.showMessageDialog(null, "Candidato cadatrado com sucesso!", "Sucesso no cadastro.", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Tipo de candidato inserido não existe ou ele já foi cadastrado anteriormente!", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
				}
			} 
			// Inicia a eleição:
			else if(escolhaOpcao.equalsIgnoreCase(opcoes[2])) {
				if(urna.getQuantidadePresidentes() < 2) {
					JOptionPane.showMessageDialog(null, "Insira no mínimo dois presidentes!", "Quantidade insuficiente de Presidentes",
							JOptionPane.WARNING_MESSAGE);
				}
				else if(urna.getQuantidadeSenadores() < 2) {
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
		
		JOptionPane.showMessageDialog(null, "Eleição foi finalizada!");
		String opcoes2[] = {"Mostrar Resultados de Todos Candidatos", "Mostrar Ganhadores", "Mostrar Estatísticas Gerais", "Sair"};
		
		while(true) {
			String escolhaOpcao1 = (String) JOptionPane.showInputDialog( null, "Funcionário, que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes2, opcoes2[3]);
			if(escolhaOpcao1.equalsIgnoreCase(opcoes2[0])) {
				//JOptionPane.showMessageDialog(null, urna.getPresidentesResultados(), "Resultados dos presidentes", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(null, urna.getSenadoresResultados(), "Resultados dos senadores", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "Ainda não implementado");
			} else if(escolhaOpcao1.equalsIgnoreCase(opcoes2[1])) {
				JOptionPane.showMessageDialog(null, "Ainda não implementado");
			}else if(escolhaOpcao1.equalsIgnoreCase(opcoes2[2])) {
				JOptionPane.showMessageDialog(null, "Ainda não implementado");
			} else if(escolhaOpcao1.equalsIgnoreCase(opcoes2[3])) {
				JOptionPane.showMessageDialog(null, "Saindo do sistema.");
				break;
			}
		}
	}

}
