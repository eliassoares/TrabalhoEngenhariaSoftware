package principal;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Integer senha = 1234567;
		Integer matricula = 2011049053;
		Urna urna = new Urna(123, "elias", 12, matricula,senha);
		
		String opcoes[] = {"Cadrastar Eleitor", "Cadrastar Candidato", "Sair"};
		
		while(true) {
			String escolhaOpcao = (String) JOptionPane.showInputDialog( null, "O que deseja fazer?", "Escolha algo", JOptionPane.QUESTION_MESSAGE, 
			        null, opcoes, opcoes[0]);
			if(escolhaOpcao.equalsIgnoreCase(opcoes[0])) {
				String nome = JOptionPane.showInputDialog(null,"Insira o nome do eleitor:");
				Integer tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o titulo do eleitor:"));
				urna.cadrastraEleitores(nome, tituloDeEleitor);
				
			} else if(escolhaOpcao.equalsIgnoreCase(opcoes[1])) {
				String nome = JOptionPane.showInputDialog(null,"Insira o nome do candidato:");
				Integer tituloDeEleitor = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o titulo do candidato:"));
				Integer numeroDeVotacao = Integer.valueOf(JOptionPane.showInputDialog(null,"Insira o numero do candidato:"));
				String partido = JOptionPane.showInputDialog(null,"Insira o partido do candidato:");
				String tipoDeCandidato = JOptionPane.showInputDialog(null,"Insira o tipo do candidato: ladrão ou assassino");
				urna.cadrastaCandidatos(nome, tituloDeEleitor, numeroDeVotacao, partido, tipoDeCandidato, null);
			} else if(escolhaOpcao.equalsIgnoreCase(opcoes[2])) {
				//TODO fazer estatisticas
			} else {
				System.out.println("Algo errado!");
				System.exit(1989);
			}
		}

	}

}
