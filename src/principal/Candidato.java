package principal;

import java.awt.image.BufferedImage;

public class Candidato extends APessoa {

	private Integer numeroDeVotacao;
	private String partido;
	private String tipoDeCandidato; // pode ser ou Presidente ou Senado
	private BufferedImage foto;

	public Candidato(String nome, Integer tituloDeEleitor,
			Integer numeroDeVotacao, String partido, String tipoDeCandidato,
			BufferedImage foto) {

		super(nome, tituloDeEleitor);

		this.numeroDeVotacao = numeroDeVotacao;
		this.partido = partido;
		this.tipoDeCandidato = tipoDeCandidato;
		// TODO ver como ler foto
		this.foto = foto;
	}

	public Integer getNumeroDeVotacao() {
		return numeroDeVotacao;
	}

	public String getPartido() {
		return partido;
	}

	public BufferedImage getFoto() {
		return foto;
	}

}
