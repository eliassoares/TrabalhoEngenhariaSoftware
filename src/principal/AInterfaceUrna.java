package principal;

import javax.swing.JOptionPane;

public class AInterfaceUrna {
	
	public static String mostraOpcoes(String mensagem, String nomeJanela,String[] opcoes, int indiceOpcoes) {
		return (String) JOptionPane.showInputDialog(null, mensagem, nomeJanela, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[indiceOpcoes]);
	}

	public static void mostraAviso(String mensagem, String nomeJanela) {
		JOptionPane.showMessageDialog(null, mensagem, nomeJanela, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void mostraInformacao(String mensagem, String nomeJanela) {
		JOptionPane.showMessageDialog(null,	mensagem, nomeJanela, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mostraErro(String mensagem, String nomeJanela) {
		JOptionPane.showMessageDialog(null, mensagem, nomeJanela, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int getConfirmacao(String mensagem, String nomeJanela) {
		return JOptionPane.showConfirmDialog(null, mensagem, nomeJanela, JOptionPane.OK_OPTION);
	}
	
	public static String getValor(String mensagem, String nomeJanela) {
		return JOptionPane.showInputDialog(null, mensagem, nomeJanela, JOptionPane.QUESTION_MESSAGE);
	}
}
