package principal;

public class Voto {

	Integer tituloEleitor;
	Integer numeroCandidato;
	String tipoCandidato; // Pode ser ou presidente ou senador

	/**
	 * 
	 * @param tituloEleitor
	 * @param numeroCandidato
	 * @param tipoCandidato
	 *            pode ser ou presidente ou senador
	 */
	public Voto(Integer tituloEleitor, Integer numeroCandidato,
			String tipoCandidato) {

		this.tituloEleitor = tituloEleitor;
		this.numeroCandidato = numeroCandidato;
		this.tipoCandidato = tipoCandidato;
	}
}
