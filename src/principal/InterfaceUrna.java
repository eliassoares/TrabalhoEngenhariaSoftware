package principal;

import javax.swing.JOptionPane;

public class InterfaceUrna {

	/**
	 * Dada uma lista de opções, mostra o usuário uma caixa de opções para que
	 * ele escolha uma.
	 * 
	 * @param mensagem
	 * @param nomeJanela
	 * @param opcoes
	 * @param indiceOpcoes
	 * @return
	 */
	public static String mostrarOpcoes(String mensagem, String nomeJanela,
			String[] opcoes, int indiceOpcoes) {
		return (String) JOptionPane.showInputDialog(null, mensagem, nomeJanela,
				JOptionPane.QUESTION_MESSAGE, null, opcoes,
				opcoes[indiceOpcoes]);
	}

	/**
	 * Mostra uma mensagem de aviso
	 * 
	 * @param mensagem
	 * @param nomeJanela
	 */
	public static void mostrarAviso(String mensagem, String nomeJanela) {
		JOptionPane.showMessageDialog(null, mensagem, nomeJanela,
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Mostra uma mensagem de informação
	 * 
	 * @param mensagem
	 * @param nomeJanela
	 */
	public static void mostrarInformacao(String mensagem, String nomeJanela) {
		JOptionPane.showMessageDialog(null, mensagem, nomeJanela,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Mostra um mensagem de erro
	 * 
	 * @param mensagem
	 * @param nomeJanela
	 */
	public static void mostrarErro(String mensagem, String nomeJanela) {
		JOptionPane.showMessageDialog(null, mensagem, nomeJanela,
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Obtém o resultado de uma pergunta de confirmação com o usuário
	 * 
	 * @param mensagem
	 * @param nomeJanela
	 * @return
	 */
	public static int getConfirmacao(String mensagem, String nomeJanela) {
		return JOptionPane.showConfirmDialog(null, mensagem, nomeJanela,
				JOptionPane.OK_OPTION);
	}

	/**
	 * Obtém o valor de uma pergunta feita ao usuário
	 * 
	 * @param mensagem
	 * @param nomeJanela
	 * @return
	 */
	public static String getValor(String mensagem, String nomeJanela) {
		return JOptionPane.showInputDialog(null, mensagem, nomeJanela,
				JOptionPane.QUESTION_MESSAGE);
	}
}
