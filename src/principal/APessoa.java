package principal;

public class APessoa {

	private String nome;
	private Integer tituloDeEleitor;

	public APessoa(String nome, Integer tituloDeEleitor) {
		this.nome = nome;
		this.tituloDeEleitor = tituloDeEleitor;
	}

	public String getNome() {
		return nome;
	}

	public Integer getTituloDeEleitor() {
		return tituloDeEleitor;
	}

}
