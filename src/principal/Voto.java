package principal;

public class Voto {

	private Integer tituloEleitor;
	private Integer numeroCandidato;
	private String tipoCandidato; // Pode ser ou presidente ou senador

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

	public Integer getTituloEleitor() {
		return this.tituloEleitor;
	}

	public Integer getNumeroCandidato() {
		return this.numeroCandidato;
	}

	public String getTipoCandidato() {
		return this.tipoCandidato;
	}
}
