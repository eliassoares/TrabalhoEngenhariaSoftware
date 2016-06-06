package principal;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Integer senha = 1234567;
		Integer matricula = 2011049053;
		String nomeFuncionario = "Alan Turing";
		Integer tituloEleitor = 2011049053;
		
		Integer numeroDeSerie = 666;
		Urna urna = new Urna(numeroDeSerie, nomeFuncionario, tituloEleitor, matricula,senha);
		
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
							JOptionPane.OK_OPTION);
				}
			} 
			else if(escolhaOpcao.equalsIgnoreCase(opcoes[3])){
				JOptionPane.showMessageDialog(null, "Saindo do sistema");
				System.exit(1989);
			}
		}

	}

}
