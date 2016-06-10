package principal;

public class Candidato extends APessoa {

	private Integer numeroDeVotacao;
	private String partido;
	private String tipoDeCandidato; // pode ser ou Presidente ou Senador

	public Candidato(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato) {

		super(nome, tituloDeEleitor);

		this.numeroDeVotacao = numeroDeVotacao;
		this.partido = partido;
		this.tipoDeCandidato = tipoDeCandidato;
		// TODO ver como ler foto
	}

	public Integer getNumeroDeVotacao() {
		return numeroDeVotacao;
	}

	public String getPartido() {
		return partido;
	}

}
